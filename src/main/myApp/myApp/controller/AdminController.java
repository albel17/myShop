package myApp.controller;

import myApp.entity.AttributesEntity;
import myApp.entity.CategoriesEntity;
import myApp.entity.ProductsEntity;
import myApp.services.CategoriesManager;
import myApp.services.OrderManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;

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

    @RequestMapping(value = "/admin/addcategory")
    public String addcategory(@RequestParam String name, @RequestParam String description) {
        categoriesManager.createByNameAndDescription(name, description);
        return "redirect:/admin/allcategories";
    }

    @RequestMapping(value = "/admin/removecategory")
    public String removecategory(@RequestParam int id) {
        categoriesManager.delete(id);
        return "redirect:/admin/allcategories";
    }

    @RequestMapping(value = "/admin/allproducts")
    public String allproducts(Model model) {
        model.addAttribute("categories", categoriesManager.getAll());
        return "allproducts";
    }

    @RequestMapping(value = "/admin/editproducts")
    public String editproducts(Model model, @RequestParam int id) {
        model.addAttribute("products", categoriesManager.getProductsById(id));
        model.addAttribute("attributes", categoriesManager.getAttributesById(id));
        model.addAttribute("newProduct", new ProductsEntity());
        return "editproducts";
    }

    @RequestMapping(value = "/admin/editcategory")
    public String editcategory(Model model, @RequestParam int id) {
        model.addAttribute("attributes", categoriesManager.getAttributesById(id));
        return "editcategory";
    }

    @RequestMapping(value = "/admin/createattribute")
    public String createattribute(@RequestParam int categoryId, @RequestParam String name, @RequestParam String description) {
        categoriesManager.createAttribute(categoryId, name, description);
        return "redirect:/admin/editcategory?id="+categoryId;
    }

    /*@RequestMapping(value = "/admin/addproduct")
    public String addproduct(@RequestParam int categoryId){
        HashMap<AttributesEntity, String> attributesAndValues = new HashMap<AttributesEntity, String>();
        for (AttributesEntity attribute : categoriesManager.find(categoryId).getAttributes()) {
            attributesAndValues.put(attribute, req.getParameter(String.valueOf(attribute.getId())));
        }

        productManager.createWithParams(req.getParameter("name"), req.getParameter("currentprice"),
                req.getParameter("size"), req.getParameter("weight"), req.getParameter("description"),
                attributesAndValues, Integer.parseInt(req.getParameter("categoryId")));

        return "redirect:/admin/editproducts?id="+categoryId;
    }*/
}
