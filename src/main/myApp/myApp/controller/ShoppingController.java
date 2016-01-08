package myApp.controller;

import myApp.bin.Cart;
import myApp.bin.CartItem;
import myApp.entity.AttributesEntity;
import myApp.entity.CategoriesEntity;
import myApp.entity.ProductsEntity;
import myApp.form.FilterForm;
import myApp.services.CategoriesManager;
import myApp.services.ProductManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class ShoppingController {

    @Resource
    private CategoriesManager categoriesManager;

    @Resource
    private Cart cart;

    @Resource
    private ProductManager productManager;

    @RequestMapping(value = "/")
    public String categories(Model model) {
        ArrayList<CategoriesEntity> categories = categoriesManager.getAll();
        model.addAttribute("categories", categories);
        return "index";
    }

    @RequestMapping(value = "/products")
    public String products(Model model, @RequestParam int id) {
        model.addAttribute("products", categoriesManager.getProductsById(id));
        model.addAttribute("id", id);
        model.addAttribute("filterForm", new FilterForm(id, 0, 0, ""));
        return "products";
    }

    @RequestMapping(value = "/productdescription")
    public String productDescription(Model model, @RequestParam int id) {
        ProductsEntity product = productManager.find(id);
        model.addAttribute("product", product);
        CategoriesEntity category = product.getCategory();
        Collection<AttributesEntity> attributes = category.getAttributes();
        model.addAttribute("attributes", attributes);
        ArrayList<String> values = new ArrayList<String>();
        for (AttributesEntity attribute : attributes) {
            values.add(productManager.getParameterByAttributeIdProductId(attribute, product));
        }
        model.addAttribute("values", values);
        model.addAttribute("categoryId", category.getId());
        model.addAttribute("inBin", cart.hasItem(product));
        return "productdescription";
    }

    @RequestMapping(value = "/profile")
    public String profile() {
        return "profile";
    }

    @RequestMapping(value = "/addtocart")
    public String addToCart(Model model, @RequestParam(value = "id") int id) {
        ProductsEntity product = productManager.find(id);
        cart.add(new CartItem(product, 1));
        model.addAttribute("id", id);
        return "redirect:/productdescription";
    }

    @RequestMapping(value = "/removefromcart")
    public String removefromcart(Model model, @RequestParam(value = "id") int id) {
        ProductsEntity product = productManager.find(id);
        cart.remove(new CartItem(product, 1));
        model.addAttribute("id", id);
        return "redirect:/productdescription";
    }

    @RequestMapping(value = "/filter")
    public String filter(Model model, @ModelAttribute FilterForm filterForm) {
        Collection<ProductsEntity> products = categoriesManager.getProductsById(filterForm.getCategoryId());
        Collection<ProductsEntity> filteredProducts = new ArrayList<ProductsEntity>();
        if (filterForm.getMaxPrice() != 0) {
            for (ProductsEntity product : products) {
                if (product.getCurrentPrice() >= filterForm.getMinPrice()
                        && product.getCurrentPrice() <= filterForm.getMaxPrice()
                        && product.getName().toLowerCase().contains(filterForm.getName().toLowerCase())) {
                    filteredProducts.add(product);
                    //product.getName().toLowerCase().contains(filterForm.getName().toLowerCase())
                }
            }
        } else {
            for (ProductsEntity product : products) {
                if (product.getCurrentPrice() >= filterForm.getMinPrice()
                        && product.getName().toLowerCase().contains(filterForm.getName().toLowerCase())) {
                    filteredProducts.add(product);
                }
            }
        }
        model.addAttribute("products", filteredProducts);
        model.addAttribute("id", filterForm.getCategoryId());
        model.addAttribute("filterForm", filterForm);
        return "products";
    }
}
