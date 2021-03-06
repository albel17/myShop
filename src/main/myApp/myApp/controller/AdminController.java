package myApp.controller;

import myApp.entity.*;
import myApp.form.ProductsMoney;
import myApp.form.UsersMoney;
import myApp.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Controller
@Transactional
public class AdminController {
    @Resource
    private OrderService orderService;

    @Resource
    private CategoriesService categoriesService;

    @Resource
    private ProductService productService;

    @Resource
    private PersonService personService;

    @Resource
    private AttributeService attributeService;

    @RequestMapping(value = "/admin")
    public String admin() {
        return "admin";
    }

    @RequestMapping(value = "/admin/allorders")
    public String allorders(Model model) {
        model.addAttribute("orderslist", orderService.getAll());
        return "allorders";
    }

    @RequestMapping(value = "/admin/editorderstatus")
    public String editorderstatus(@RequestParam(value = "orderid") int orderid,
                                  @RequestParam(value = "status") String status) {
        orderService.changeStatus(orderid, status);
        return "redirect:/admin/allorders";
    }

    @RequestMapping(value = "/admin/allcategories")
    public String allcategories(Model model) {
        ArrayList<CategoriesEntity> categories = categoriesService.getAll();
        model.addAttribute("categories", categories);
        model.addAttribute("isEmpty", false);
        model.addAttribute("isUnique", true);
        return "allcategories";
    }

    @RequestMapping(value = "/admin/addcategory")
    public String addcategory(Model model, @RequestParam String name, @RequestParam String description) {
        if (!"".equals(name) && !"".equals(description) && !categoriesService.hasCategory(name)) {
            categoriesService.createByNameAndDescription(name, description);
        } else {
            model.addAttribute("isEmpty", false);
            model.addAttribute("isUnique", true);
            if ("".equals(name) || "".equals(description)) {
                model.addAttribute("isEmpty", true);
            } else {
                model.addAttribute("isUnique", false);
            }
            ArrayList<CategoriesEntity> categories = categoriesService.getAll();
            model.addAttribute("categories", categories);
            return "allcategories";
        }
        return "redirect:/admin/allcategories";
    }

    @RequestMapping(value = "/admin/removecategory")
    public String removecategory(@RequestParam int id) {
        categoriesService.delete(id);
        return "redirect:/admin/allcategories";
    }

    @RequestMapping(value = "/admin/allproducts")
    public String allproducts(Model model) {
        model.addAttribute("categories", categoriesService.getAll());
        return "allproducts";
    }

    @RequestMapping(value = "/admin/editproducts")
    public String editproducts(Model model, @RequestParam int id) {
        model.addAttribute("products", categoriesService.getProductsById(id));
        model.addAttribute("attributes", categoriesService.getAttributesById(id));
        model.addAttribute("newProduct", new NewProduct());
        return "editproducts";
    }

    @RequestMapping(value = "/admin/editcategory")
    public String editcategory(Model model, @RequestParam int id, @RequestParam String isEmpty) {
        model.addAttribute("attributes", categoriesService.getAttributesById(id));
        if ("true".equals(isEmpty))
            model.addAttribute("isEmpty", true);
        else
            model.addAttribute("isEmpty", false);
        return "editcategory";
    }

    @RequestMapping(value = "/admin/createattribute")
    public String createattribute(@RequestParam int categoryId, @RequestParam String name, @RequestParam String description) {
        if (!"".equals(name) && !"".equals(description)) {
            categoriesService.createAttribute(categoryId, name, description);
            return "redirect:/admin/editcategory?id=" + categoryId + "&isEmpty=false";
        } else {
            return "redirect:/admin/editcategory?id=" + categoryId + "&isEmpty=true";
        }
    }

    @RequestMapping(value = "/admin/removeproduct")
    public String removeproduct(@RequestParam int id) {
        int i = productService.getCategoryId(id);
        productService.delete(id);
        return "redirect:/admin/editproducts?id=" + i;
    }

    @RequestMapping(value = "/admin/removeattribute")
    public String removeattribute(@RequestParam int id) {
        AttributesEntity attribute = attributeService.find(id);
        CategoriesEntity category = new CategoriesEntity();
        for (CategoriesEntity categoriesEntity : attribute.getCategories()) {
            category = categoriesEntity;
        }
        Collection<AttributesEntity> attributes = category.getAttributes();
        attributes.remove(attribute);
        category.setAttributes(attributes);
        categoriesService.update(category);
        attributeService.delete(id);
        return "redirect:/admin/editcategory?id=" + category.getId() + "&isEmpty=false";
    }

    @RequestMapping(value = "/admin/editproduct")
    public String editproduct(Model model, @RequestParam int id) {
        ProductsEntity product = productService.find(id);
        model.addAttribute("product", product);
        CategoriesEntity category = product.getCategory();
        Collection<AttributesEntity> attributes = category.getAttributes();
        model.addAttribute("attributes", attributes);
        ArrayList<String> values = new ArrayList<>();
        for (AttributesEntity attribute : attributes) {
            values.add(attributeService.getParameterByAttributeIdProductId(attribute, product).getValue());
        }
        model.addAttribute("values", values);
        model.addAttribute("amount", product.getStoragesById().getAmount());
        return "editproduct";
    }

    @RequestMapping(value = "/admin/addproduct")
    public String addproduct(@RequestParam int amount, @RequestParam int categoryId, @ModelAttribute NewProduct newProduct) {
        HashMap<AttributesEntity, String> attributesAndValues = new HashMap<>();
        int i = 0;
        for (AttributesEntity attribute : categoriesService.find(categoryId).getAttributes()) {
            attributesAndValues.put(attribute, newProduct.getNewAttributes().get(i));
        }

        productService.createWithParams(newProduct.getName(), newProduct.getCurrentPrice(),
                newProduct.getSize(), newProduct.getWeight(), newProduct.getDescription(),
                attributesAndValues, categoryId, amount);

        return "redirect:/admin/editproducts?id=" + categoryId;
    }

    @RequestMapping(value = "/admin/submitproductchange")
    public String submitproductchange(@RequestParam int productId, @RequestParam String name,
                                      @RequestParam int currentprice, @RequestParam int size, @RequestParam int weight,
                                      @RequestParam String description, @RequestParam int amount,
                                      HttpServletRequest request) {
        productService.changeProduct(productId, name, currentprice, size, weight, description, amount, request);
        return "redirect:/admin/editproduct?id=" + productId;
    }

    @RequestMapping(value = "/admin/statistics")
    public String statistics(Model model){
        List<PersonsEntity> customers = personService.getTopCustomers();
        ArrayList<UsersMoney> topClients = new ArrayList<>();
        for(PersonsEntity customer : customers){
            topClients.add(new UsersMoney(customer, personService.getUsersMoney(customer)));
        }
        model.addAttribute("topClients", topClients);

        List<ProductsEntity> products = productService.getTopProducts();
        ArrayList<ProductsMoney> topProducts = new ArrayList<>();
        for(ProductsEntity product : products){
            topProducts.add(new ProductsMoney(product, productService.getAllMoneyForProduct(product)));
        }
        model.addAttribute("topProducts", topProducts);

        model.addAttribute("moneyForMonth", productService.getAllMoneyThisMonth());
        model.addAttribute("moneyForWeek", productService.getAllMoneyThisWeek());
        return "statistics";
    }
}
