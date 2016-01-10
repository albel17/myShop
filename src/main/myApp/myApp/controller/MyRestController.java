package myApp.controller;

import myApp.entity.PersonsEntity;
import myApp.entity.ProductsEntity;
import myApp.form.RestForm;
import myApp.services.PersonManager;
import myApp.services.ProductManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MyRestController {

    @Resource
    private PersonManager personManager;

    @Resource
    private ProductManager productManager;

    @RequestMapping("/greeting")
    public RestForm greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        ArrayList<String> topClientsEmails = new ArrayList<String>();
        ArrayList<String> topClientsMoney = new ArrayList<String>();
        ArrayList<String> topProductsNames = new ArrayList<String>();
        ArrayList<String> topProductsPrices = new ArrayList<String>();
        ArrayList<String> topProductsMoney = new ArrayList<String>();

        List<PersonsEntity> customers = personManager.getTopCustomers();
        for (PersonsEntity customer : customers) {
            topClientsEmails.add(customer.getName());
            topClientsMoney.add(String.valueOf(personManager.getUsersMoney(customer)));
        }

        List<ProductsEntity> products = productManager.getTopProducts();
        for (ProductsEntity product : products) {
            topProductsNames.add(product.getName());
            topProductsPrices.add(String.valueOf(product.getCurrentPrice()));
            topProductsMoney.add(String.valueOf(productManager.getAllMoneyForProduct(product)));
        }

        return new RestForm(topClientsEmails, topClientsMoney, topProductsNames, topProductsPrices, topProductsMoney, productManager.getAllMoneyThisMonth(), productManager.getAllMoneyThisWeek());
    }
}
