package myApp.controller;

import myApp.bin.Cart;
import myApp.bin.CartItem;
import myApp.entity.AddressesEntity;
import myApp.entity.PersonsEntity;
import myApp.form.RegistrationForm;
import myApp.services.AddressManager;
import myApp.services.OrderManager;
import myApp.services.PersonManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.validation.Valid;
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
    private UserDetailsService userDetailsService;

    @Resource
    private Cart cart;

    @RequestMapping(value = "/registration")
    public String registration(Model model) {
        RegistrationForm person = new RegistrationForm();
        model.addAttribute("person", person);
        model.addAttribute("emailExists", false);
        return "registration";
    }

    @RequestMapping(value = "/reg")
    public String reg(@ModelAttribute("person") @Valid RegistrationForm person, BindingResult bindingResult, Model model) {

        if (!bindingResult.hasErrors() && !personManager.hasPerson(person.getEmail())) {
            personManager.createWithParams(person.getName(), person.getSurname(), person.getBirthdate(),
                    person.getEmail(), person.getPassword());
            UserDetails userDetails = userDetailsService.loadUserByUsername(person.getEmail());
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
                    userDetails, person.getPassword(), userDetails.getAuthorities()));
            return "profile";
        }
        boolean emailExists = false;
        if (personManager.hasPerson(person.getEmail())) {
            emailExists = true;
        }
        model.addAttribute("emailExists", emailExists);
        model.addAttribute("person", person);
        return "registration";
    }

    @RequestMapping(value = "/profile/edituserinfo")
    public String edituserinfo(ModelMap model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        RegistrationForm form = new RegistrationForm();
        PersonsEntity person = personManager.getPersonByEmail(user.getUsername());
        form.setName(person.getName());
        form.setSurname(person.getSurname());
        form.setBirthdate(person.getBirthdate());
        form.setEmail(person.getEmail());
        form.setNewEmail(person.getEmail());
        model.addAttribute("person", form);
        return "edituserinfo";
    }

    @RequestMapping(value = "/profile/submituserchange")
    public String submituserchange(@ModelAttribute(value = "person") @Valid RegistrationForm form,
                                   BindingResult bindingResult, Model model) {
        if(!bindingResult.hasErrors()) {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            PersonsEntity person = personManager.getPersonByEmail(user.getUsername());
            System.out.println(person.getPassword());
            System.out.println(form.getPassword());
            if (person.getPassword().equals(form.getPassword())) {
                person.setName(form.getName());
                person.setSurname(form.getSurname());
                person.setBirthdate(form.getBirthdate());
                person.setEmail(form.getEmail());
                person.setPassword(form.getNewPassword());
                personManager.update(person);
            }
            return "redirect:/profile/edituserinfo";
        }
        model.addAttribute("person", form);
        return "edituserinfo";
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
                personManager.getPersonByEmail(user.getUsername()).getId(), address);
        cart.nullify();
        return "redirect:/profile/";
    }

    @RequestMapping(value = "/profile/myorders")
    public String myorders(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("orderslist", personManager.getOrders(personManager.getPersonByEmail(user.getUsername()).getId()));
        return "myorders";
    }

}
