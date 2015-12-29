package myApp.controller;

import myApp.entity.CategoriesEntity;
import myApp.services.CategoriesManager;
import myApp.services.OrderManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.ArrayList;

@Controller
@Transactional
public class AdminController {
    @Resource
    private OrderManager orderManager;

    @Resource
    private CategoriesManager categoriesManager;

    @RequestMapping(value = "/admin")
    public String admin() {
        return "admin";
    }

    @RequestMapping(value = "/admin/allorders")
    public String allorders(Model model) {
        model.addAttribute("orderslist", orderManager.getAll());
        return "allorders";
    }

    @RequestMapping(value = "/admin/editorderstatus")
    public String editorderstatus(@RequestParam(value = "orderid") int orderid,
                                  @RequestParam(value = "status") String status) {
        orderManager.changeStatus(orderid, status);
        return "redirect:/admin/allorders";
    }

    @RequestMapping(value = "/admin/allcategories")
    public String allcategories(Model model) {
        ArrayList<CategoriesEntity> categories = categoriesManager.getAll();
        model.addAttribute("categories", categories);
        return "allcategories";
    }
}
