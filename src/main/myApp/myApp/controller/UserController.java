package myApp.controller;

import myApp.entity.AddressesEntity;
import myApp.entity.PersonsEntity;
import myApp.services.AddressManager;
import myApp.services.PersonManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@Transactional
public class UserController {
    @Resource
    private PersonManager personManager;

    @Resource
    private AddressManager addressManager;

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

}
