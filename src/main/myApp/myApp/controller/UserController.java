package myApp.controller;

import myApp.bin.Cart;
import myApp.bin.CartItem;
import myApp.entity.AddressesEntity;
import myApp.entity.PersonsEntity;
import myApp.form.AddressForm;
import myApp.form.RegistrationForm;
import myApp.services.AddressService;
import myApp.services.OrderService;
import myApp.services.PersonService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
import java.util.Objects;

@Controller
@Transactional
public class UserController {
    @Resource
    private PersonService personService;

    @Resource
    private AddressService addressManager;

    @Resource
    private OrderService orderService;

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

        if (!bindingResult.hasErrors() && !personService.hasPerson(person.getEmail())) {
            personService.createWithParams(person.getName(), person.getSurname(), person.getBirthdate(),
                    person.getEmail(), person.getPassword());
            UserDetails userDetails = userDetailsService.loadUserByUsername(person.getEmail());
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
                    userDetails, person.getPassword(), userDetails.getAuthorities()));
            return "profile";
        }
        boolean emailExists = false;
        if (personService.hasPerson(person.getEmail())) {
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
        PersonsEntity person = personService.getPersonByEmail(user.getUsername());
        form.setName(person.getName());
        form.setSurname(person.getSurname());
        form.setBirthdate(person.getBirthdate());
        form.setEmail(person.getEmail());
        form.setNewEmail(person.getEmail());
        form.setPassword(person.getPassword());
        model.addAttribute("person", form);
        model.addAttribute("emailExists", false);
        return "edituserinfo";
    }

    @RequestMapping(value = "/profile/submituserchange")
    public String submituserchange(@ModelAttribute(value = "person") @Valid RegistrationForm form,
                                   BindingResult bindingResult, Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!bindingResult.hasErrors()) {
            PersonsEntity person = personService.getPersonByEmail(user.getUsername());
            person.setName(form.getName());
            person.setSurname(form.getSurname());
            person.setBirthdate(form.getBirthdate());
            person.setEmail(form.getEmail());
            person.setPassword(form.getNewPassword());
            personService.update(person);
            return "redirect:/profile/edituserinfo";
        }
        boolean emailExists = false;
        if (personService.hasPerson(form.getEmail())) {
            emailExists = true;
        }
        model.addAttribute("person", form);
        model.addAttribute("emailExists", emailExists);
        return "edituserinfo";
    }

    @RequestMapping(value = "/profile/addresslist")
    public String addresslist(Model model, @RequestParam(required = false, defaultValue = "") String error) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("addresslist", personService.getPersonByEmail(user.getUsername()).getAddressesById());
        model.addAttribute("newaddress", new AddressForm());
        if (Objects.equals(error, ""))
            model.addAttribute("errors", false);
        else {
            model.addAttribute("errors", true);
        }
        return "addresslist";
    }

    @RequestMapping(value = "/profile/addaddress")
    public String addaddress(@ModelAttribute(value = "newaddress") @Valid AddressForm form, BindingResult bindingResult,
                             Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!bindingResult.hasErrors()) {
            AddressesEntity address = new AddressesEntity();
            address.setCountry(form.getCountry());
            address.setCity(form.getCity());
            address.setPostalCode(form.getPostalCode());
            address.setStreet(form.getStreet());
            address.setHouse(form.getHouse());
            address.setFlat(form.getFlat());
            addressManager.createWithParams(address.getCountry(), address.getCity(), address.getPostalCode(),
                    address.getStreet(), address.getHouse(), address.getFlat(),
                    personService.getPersonByEmail(user.getUsername()).getId());
            return "redirect:/profile/addresslist";
        }
        model.addAttribute("newaddress", form);
        model.addAttribute("addresslist", personService.getPersonByEmail(user.getUsername()).getAddressesById());
        return "addresslist";
    }

    @RequestMapping(value = "/profile/deleteaddress")
    public String deleteaddress(Model model, @RequestParam(value = "id") int id) {
        if (addressManager.find(id).getOrdersesById().isEmpty()) {
            addressManager.delete(id);
            return "redirect:/profile/addresslist";
        } else {
            return "redirect:/profile/addresslist?error=true";
        }
    }

    @RequestMapping(value = "/profile/checkout")
    public String checkout(Model model) {
        if (cart.ifEmpty()) {
            return "redirect:/profile/";
        } else {
            ArrayList<CartItem> cartItems = cart.getItems();
            model.addAttribute("cartItems", cartItems);
            model.addAttribute("sum", cart.getSum());
            model.addAttribute("isFuture", true);
            model.addAttribute("amountErrorString", "");
            return "checkout";
        }
    }

    @RequestMapping(value = "/profile/checkoutcontinue")
    public String checkoutcontinue(Model model, @RequestParam(value = "deliverymethod") String deliverymethod,
                                   @RequestParam(value = "paymentmethod") String paymentmethod,
                                   @RequestParam(value = "deliverydate") java.sql.Date deliverydate) {
        if (deliverydate.getTime() < (new Date().getTime())) {
            ArrayList<CartItem> cartItems = cart.getItems();
            model.addAttribute("cartItems", cartItems);
            model.addAttribute("sum", cart.getSum());
            model.addAttribute("isFuture", false);
            return "checkout";
        }
        boolean hadAmountErrors = false;
        String amountErrorString = "";
        for (CartItem cartItem : cart.getItems()) {
            if (cartItem.getProduct().getStoragesById().getAmount() < cartItem.getAmount()) {
                amountErrorString += "We have only " + cartItem.getProduct().getStoragesById().getAmount() + " "
                        + cartItem.getProduct().getName() + " right now.\n";
                hadAmountErrors = true;
            }
        }
        if (hadAmountErrors) {
            ArrayList<CartItem> cartItems = cart.getItems();
            model.addAttribute("cartItems", cartItems);
            model.addAttribute("sum", cart.getSum());
            model.addAttribute("isFuture", true);
            model.addAttribute("amountErrorString", amountErrorString);
            return "checkout";
        }

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("paymentmethod", paymentmethod);
        model.addAttribute("deliverymethod", deliverymethod);
        model.addAttribute("deliverydate", deliverydate);
        if (deliverymethod.equals("delivery")) {
            model.addAttribute("addresslist",
                    addressManager.getAddressListByUserId(personService.getPersonByEmail(user.getUsername()).getId()));
        }
        return "checkoutcontinue";
    }

    @RequestMapping(value = "/profile/createorder")
    public String createorder(@RequestParam(value = "paymentmethod") String paymentmethod,
                              @RequestParam(value = "deliverymethod") String deliverymethod,
                              @RequestParam(value = "address") int address,
                              @RequestParam(value = "deliverydate") java.sql.Date deliverydate) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        orderService.createWithParams(paymentmethod, deliverymethod, deliverydate, cart,
                personService.getPersonByEmail(user.getUsername()).getId(), address);
        cart.nullify();
        return "redirect:/profile/";
    }

    @RequestMapping(value = "/profile/myorders")
    public String myorders(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("orderslist", personService.getOrders(personService.getPersonByEmail(user.getUsername()).getId()));
        return "myorders";
    }

}
