package myApp.controller;

import myApp.entity.PersonsEntity;
import myApp.entity.ProductsEntity;
import myApp.form.RestForm;
import myApp.services.PersonService;
import myApp.services.ProductService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MyRestController {

    @Resource
    private PersonService personService;

    @Resource
    private ProductService productService;

    @RequestMapping("/greeting")
    public RestForm greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        ArrayList<String> topClientsEmails = new ArrayList<>();
        ArrayList<String> topClientsMoney = new ArrayList<>();
        ArrayList<String> topProductsNames = new ArrayList<>();
        ArrayList<String> topProductsPrices = new ArrayList<>();
        ArrayList<String> topProductsMoney = new ArrayList<>();

        List<PersonsEntity> customers = personService.getTopCustomers();
        for (PersonsEntity customer : customers) {
            topClientsEmails.add(customer.getName());
            topClientsMoney.add(String.valueOf(personService.getUsersMoney(customer)));
        }

        List<ProductsEntity> products = productService.getTopProducts();
        for (ProductsEntity product : products) {
            topProductsNames.add(product.getName());
            topProductsPrices.add(String.valueOf(product.getCurrentPrice()));
            topProductsMoney.add(String.valueOf(productService.getAllMoneyForProduct(product)));
        }

        return new RestForm(topClientsEmails, topClientsMoney, topProductsNames, topProductsPrices, topProductsMoney, productService.getAllMoneyThisMonth(), productService.getAllMoneyThisWeek());
    }
}
