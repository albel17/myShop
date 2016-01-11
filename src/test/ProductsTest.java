import myApp.entity.CategoriesEntity;
import myApp.entity.ProductsEntity;
import myApp.services.CategoriesService;
import myApp.services.ProductService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Transactional
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/spring-config.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@SuppressWarnings("deprecation")
public class ProductsTest {

    @Resource
    private ProductService productService;

    @Resource
    private CategoriesService categoriesService;

    @Test
    public void create() {
        CategoriesEntity categoriesEntity = new CategoriesEntity("category", "description");
        categoriesService.create(categoriesEntity);
        ProductsEntity productsEntity = new ProductsEntity("product", 10, 1, 1, "test product", categoriesEntity);
        ProductsEntity productsEntity2 = productService.create(productsEntity);
        Assert.assertEquals(productService.find(productsEntity2.getId()).getName(), "product");
    }

    @Test(expected = NullPointerException.class)
    public void delete() {
        CategoriesEntity categoriesEntity = new CategoriesEntity("category", "description");
        categoriesService.create(categoriesEntity);
        ProductsEntity productsEntity = new ProductsEntity("product", 10, 1, 1, "test product", categoriesEntity);
        ProductsEntity productsEntity2 = productService.create(productsEntity);
        productService.delete(productsEntity2.getId());
        productService.find(productsEntity2.getId());
    }

    @Test
    public void update() {
        CategoriesEntity categoriesEntity = new CategoriesEntity("category", "description");
        categoriesService.create(categoriesEntity);
        ProductsEntity productsEntity = new ProductsEntity("product", 10, 1, 1, "test product", categoriesEntity);
        ProductsEntity productsEntity2 = productService.create(productsEntity);
        productsEntity2.setName("new name");
        productService.update(productsEntity2);
        Assert.assertEquals(productService.find(productsEntity2.getId()).getName(), "new name");
    }
}
