package myApp.controller;

import myApp.bin.Cart;
import myApp.bin.CartItem;
import myApp.entity.AttributesEntity;
import myApp.entity.CategoriesEntity;
import myApp.entity.ProductsEntity;
import myApp.form.AttributeAndValue;
import myApp.form.FilterForm;
import myApp.services.CategoriesService;
import myApp.services.ProductService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;

@Controller
public class ShoppingController {

    @Resource
    private CategoriesService categoriesService;

    @Resource
    private Cart cart;

    @Resource
    private ProductService productService;

    @RequestMapping(value = "/")
    public String categories(Model model) {
        ArrayList<CategoriesEntity> categories = categoriesService.getAll();
        model.addAttribute("categories", categories);
        model.addAttribute("cart", cart);
        Logger logger = Logger.getLogger(ShoppingController.class);
        logger.debug("Hi!, i'm your new logger!");
        logger.info("LOGGER INFO!");
        return "index";
    }

    @RequestMapping(value = "/products")
    public String products(Model model, @RequestParam int id) {
        model.addAttribute("products", categoriesService.getProductsById(id));
        model.addAttribute("id", id);
        model.addAttribute("filterForm", new FilterForm(id, 0, 0, "", categoriesService.find(id)));
        model.addAttribute("cart", cart);
        return "products";
    }

    @RequestMapping(value = "/productdescription")
    public String productDescription(Model model, @RequestParam int id) {
        ProductsEntity product = productService.find(id);
        model.addAttribute("product", product);
        CategoriesEntity category = product.getCategory();
        Collection<AttributesEntity> attributes = category.getAttributes();
        model.addAttribute("attributes", attributes);
        ArrayList<String> values = new ArrayList<>();
        for (AttributesEntity attribute : attributes) {
            values.add(productService.getParameterByAttributeIdProductId(attribute, product));
        }
        model.addAttribute("values", values);
        model.addAttribute("categoryId", category.getId());
        model.addAttribute("inBin", cart.hasItem(product));
        model.addAttribute("cart", cart);
        return "productdescription";
    }

    @RequestMapping(value = "/profile")
    public String profile() {
        return "profile";
    }

    @RequestMapping(value = "/addtocart")
    public String addToCart(Model model, @RequestParam(value = "id") int id,
                            @RequestParam(value = "index", required = false, defaultValue = "0") int index,
                            @RequestParam(value = "category", required = false, defaultValue = "0") int categoryId) {
        ProductsEntity product = productService.find(id);
        cart.add(new CartItem(product, 1));
        model.addAttribute("id", id);
        if (index == 0 && categoryId==0)
            return "redirect:/productdescription";
        else if(index==1)
            return "redirect:/";
        else {
            model.addAttribute("id", categoryId);
            return "redirect:/products";
        }
    }

    @RequestMapping(value = "/removefromcart")
    public String removefromcart(Model model, @RequestParam(value = "id") int id,
                                 @RequestParam(value = "index", required = false, defaultValue = "0") int index,
                                 @RequestParam(value = "category", required = false, defaultValue = "0") int categoryId) {
        ProductsEntity product = productService.find(id);
        cart.remove(new CartItem(product, 1));
        model.addAttribute("id", id);
        if (index == 0 && categoryId==0)
            return "redirect:/productdescription";
        else if(index==1)
            return "redirect:/";
        else {
            model.addAttribute("id", categoryId);
            return "redirect:/products";
        }
    }

    @RequestMapping(value = "/filter")
    public String filter(Model model, @ModelAttribute FilterForm filterForm) {
        filterForm.update(categoriesService.find(filterForm.getCategoryId()));
        Collection<ProductsEntity> products = categoriesService.getProductsById(filterForm.getCategoryId());
        Collection<ProductsEntity> filteredProducts = new ArrayList<>();
        if (filterForm.getMaxPrice() != 0) {
            for (ProductsEntity product : products) {
                if (product.getCurrentPrice() >= filterForm.getMinPrice()
                        && product.getCurrentPrice() <= filterForm.getMaxPrice()
                        && product.getName().toLowerCase().contains(filterForm.getName().toLowerCase())) {
                    filteredProducts.add(product);
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
        Collection<ProductsEntity> filteredProducts2 = new ArrayList<>(filteredProducts);
        for (AttributeAndValue attributeAndValue : filterForm.getAttributesAndValues()) {
            if (!attributeAndValue.getValue().equals("")) {
                for (ProductsEntity product : filteredProducts) {
                    if (!productService.getParameterByAttributeIdProductId(attributeAndValue.getAttribute(), product)
                            .toLowerCase().contains(attributeAndValue.getValue().toLowerCase())) {
                        filteredProducts2.remove(product);
                    }
                }
            }
        }
        model.addAttribute("products", filteredProducts2);
        model.addAttribute("id", filterForm.getCategoryId());
        model.addAttribute("filterForm", filterForm);
        model.addAttribute("cart", cart);
        return "products";
    }
}
