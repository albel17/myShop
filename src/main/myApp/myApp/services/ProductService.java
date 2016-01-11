package myApp.services;

import myApp.DAO.API.CategoriesDAO;
import myApp.DAO.OrdersDAO;
import myApp.DAO.ParametersDAO;
import myApp.DAO.ProductsDAO;
import myApp.DAO.StorageDAO;
import myApp.entity.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Component
public class ProductService implements GenericService<ProductsEntity> {
    @Resource
    private ProductsDAO productsDAO;

    @Resource
    private ParametersDAO parametersDAO;

    @Resource
    private CategoriesDAO categoriesDAO;

    @Resource
    private StorageDAO storageDAO;

    @Resource
    private OrdersDAO ordersDAO;

    @Override
    public ProductsEntity create(ProductsEntity productsEntity) {
        return productsDAO.create(productsEntity);
    }

    @Override
    public void delete(Object id) {
        ProductsEntity product = productsDAO.find(id);
        Collection<ParametersEntity> parameters = product.getParametersesById();
        for (ParametersEntity parameter : parameters) {
            parametersDAO.delete(parameter.getId());
        }
        storageDAO.delete(product.getStoragesById().getId());
        productsDAO.delete(product.getId());
    }

    @Override
    public ProductsEntity find(Object id) {
        return productsDAO.find(id);
    }

    @Override
    public ProductsEntity update(ProductsEntity productsEntity) {
        return productsDAO.update(productsEntity);
    }

    public ProductsEntity createWithParams(String name, int currentprice, int size, int weight,
                                           String description, HashMap<AttributesEntity, String> attributesAndValues,
                                           int categoryId, int amount) {

        ProductsEntity newProduct = new ProductsEntity(name, currentprice, size, weight, description,
                categoriesDAO.getCategoryByID(categoryId));
        ArrayList<ParametersEntity> parametersEntities = new ArrayList<>();
        for (Map.Entry<AttributesEntity, String> entry : attributesAndValues.entrySet()) {
            parametersEntities.add(new ParametersEntity(entry.getValue(), newProduct, entry.getKey()));
        }
        newProduct.setParametersesById(parametersEntities);
        newProduct = productsDAO.create(newProduct);
        for (ParametersEntity parametersEntity : parametersEntities) {
            parametersDAO.create(parametersEntity);
        }
        StorageEntity storage = new StorageEntity(amount, newProduct);
        storageDAO.create(storage);
        return newProduct;
    }

    public int getCategoryId(int id) {
        return productsDAO.getProductByID(id).getCategory().getId();
    }

    public String getParameterByAttributeIdProductId(AttributesEntity atr, ProductsEntity prod) {
        return parametersDAO.getParameterByAttributeIdProductId(atr, prod).getValue();
    }

    public ProductsEntity changeProduct(int id, String name, int currentprice, int size, int weight, String description,
                                        int amount, HttpServletRequest req) {
        ProductsEntity product = productsDAO.getProductByID(id);
        product.setName(name);
        product.setCurrentPrice(currentprice);
        product.setSize(size);
        product.setWeight(weight);
        product.setDescription(description);
        product.getStoragesById().setAmount(amount);

        CategoriesEntity category = productsDAO.getProductByID(id).getCategory();
        Collection<AttributesEntity> attributes = category.getAttributes();
        Map<AttributesEntity, String> attributeValues = new HashMap<>();
        for (AttributesEntity attribute : attributes) {
            attributeValues.put(attribute, req.getParameter(String.valueOf(attribute.getId())));
        }

        product = productsDAO.update(product);

        for (Map.Entry<AttributesEntity, String> entry : attributeValues.entrySet()) {
            ParametersEntity parameter = parametersDAO.getParameterByAttributeIdProductId(entry.getKey(), product);
            parameter.setValue(entry.getValue());
            parametersDAO.update(parameter);
        }
        return product;
    }

    public List<ProductsEntity> getTopProducts() {
        ArrayList<ProductsEntity> allProducts = productsDAO.getAll();
        Collections.sort(allProducts, new Comparator<ProductsEntity>() {
            public int compare(ProductsEntity o1, ProductsEntity o2) {
                int sum1 = getAllMoneyForProduct(o1);
                int sum2 = getAllMoneyForProduct(o2);
                if (sum1 == sum2)
                    return 0;
                else
                    return sum1 < sum2 ? 1 : -1;
            }
        });
        if (allProducts.size() > 10)
            return allProducts.subList(0, 10);
        else
            return allProducts;
    }

    public int getAllMoneyForProduct(ProductsEntity product) {
        int amount = 0;
        for (OrderItemEntity item : product.getOrderItemsById()) {
            amount += item.getAmount();
        }
        return amount * product.getCurrentPrice();
    }

    public int getAllMoneyThisMonth() {
        int sum = 0;
        ArrayList<OrdersEntity> orders = ordersDAO.getThisMonthOrders();
        for (OrdersEntity order : orders) {
            sum += order.getCost();
        }
        return sum;
    }

    public int getAllMoneyThisWeek() {
        int sum = 0;
        ArrayList<OrdersEntity> orders = ordersDAO.getThisWeekOrders();
        for (OrdersEntity order : orders) {
            sum += order.getCost();
        }
        return sum;
    }
}
