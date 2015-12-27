package myApp.controller;

import myApp.entity.PersonsEntity;
import myApp.services.PersonManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@Transactional
public class UserController {
    @Resource
    private PersonManager personManager;

    @RequestMapping(value = "/registration")
    public String registration(Model model){
        PersonsEntity user = new PersonsEntity();
        model.addAttribute("userForm", user);
        return "registration";
    }

    @RequestMapping(value = "/reg")
    public String reg(@ModelAttribute PersonsEntity person){
        personManager.create(person);
        return "profile";
    }


}
