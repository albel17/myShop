package myApp.controller;

import myApp.bin.Cart;
import myApp.bin.CartItem;
import myApp.entity.AddressesEntity;
import myApp.entity.PersonsEntity;
import myApp.services.AddressManager;
import myApp.services.OrderManager;
import myApp.services.PersonManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;

@Controller
@Transactional
public class UserController {
    @Resource
    private PersonManager personManager;

    @Resource
    private AddressManager addressManager;

    @Resource
    private OrderManager orderManager;

    @Resource
    private Cart cart;

    @RequestMapping(value = "/registration")
    public String registration(Model model) {
        PersonsEntity user = new PersonsEntity();
        model.addAttribute("userForm", user);
        return "registration";
    }

    @RequestMapping(value = "/reg")
    public String reg(@ModelAttribute PersonsEntity person) {
        personManager.createWithParams(person.getName(), person.getSurname(), person.getBirthdate(), person.getEmail(),
                person.getPassword());
        return "profile";
    }

    @RequestMapping(value = "/profile/edituserinfo")
    public String edituserinfo(ModelMap model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("person", personManager.getPersonByEmail(user.getUsername()));
        return "edituserinfo";
    }

    @RequestMapping(value = "/profile/submituserchange")
    public String submituserchange(@ModelAttribute PersonsEntity person) {
        personManager.update(person);
        return "redirect:/profile/edituserinfo";
    }

    @RequestMapping(value = "/profile/addresslist")
    public String addresslist(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("addresslist", personManager.getPersonByEmail(user.getUsername()).getAddressesById());
        model.addAttribute("newaddress", new AddressesEntity());
        return "addresslist";
    }

    @RequestMapping(value = "/profile/addaddress")
    public String addaddress(@ModelAttribute AddressesEntity address) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        addressManager.createWithParams(address.getCountry(), address.getCity(), address.getPostalCode(),
                address.getStreet(), address.getHouse(), address.getFlat(),
                personManager.getPersonByEmail(user.getUsername()).getId());
        return "redirect:/profile/addresslist";
    }

    @RequestMapping(value = "/profile/deleteaddress")
    public String deleteaddress(@RequestParam(value = "id") int id) {
        addressManager.delete(id);
        return "redirect:/profile/addresslist";
    }

    @RequestMapping(value = "/profile/checkout")
    public String checkout(Model model) {
        if (cart.ifEmpty()) {
            return "redirect:/profile/";
        } else {
            ArrayList<CartItem> cartItems = cart.getItems();
            model.addAttribute("cartItems", cartItems);
            model.addAttribute("sum", cart.getSum());
            return "checkout";
        }
    }

    @RequestMapping(value = "/profile/checkoutcontinue")
    public String checkoutcontinue(Model model, @RequestParam(value = "deliverymethod") String deliverymethod,
                                   @RequestParam(value = "paymentmethod") String paymentmethod) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("paymentmethod", paymentmethod);
        model.addAttribute("deliverymethod", deliverymethod);
        if (deliverymethod.equals("delivery")) {
            model.addAttribute("addresslist",
                    addressManager.getAddressListByUserId(personManager.getPersonByEmail(user.getUsername()).getId()));
        }
        return "checkoutcontinue";
    }

    @RequestMapping(value = "/profile/createorder")
    public String createorder(@RequestParam(value = "paymentmethod") String paymentmethod,
                              @RequestParam(value = "deliverymethod") String deliverymethod,
                              @RequestParam(value = "address") int address) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        orderManager.createWithParams(paymentmethod, deliverymethod, String.valueOf(new Date()), cart,
                personManager.getPersonByEmail(user.getUsername()).getId(),address);
        cart.nullify();
        return "redirect:/profile/";
    }

    @RequestMapping(value = "/profile/myorders")
    public String myorders(Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("orderslist", personManager.getOrders(personManager.getPersonByEmail(user.getUsername()).getId()));
        return "myorders";
    }

}
