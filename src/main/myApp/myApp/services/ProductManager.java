package myApp.services;

import myApp.DAO.API.CategoriesDAO;
import myApp.DAO.ParametersDAO;
import myApp.DAO.ProductsDAO;
import myApp.DAO.StorageDAO;
import myApp.entity.AttributesEntity;
import myApp.entity.ParametersEntity;
import myApp.entity.ProductsEntity;
import myApp.entity.StorageEntity;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class ProductManager implements GenericManager<ProductsEntity> {
    @Resource
    private ProductsDAO productsDAO;

    @Resource
    private ParametersDAO parametersDAO;

    @Resource
    private CategoriesDAO categoriesDAO;

    @Resource
    private StorageDAO storageDAO;

    public ProductsEntity create(ProductsEntity productsEntity) {
        return productsDAO.create(productsEntity);
    }

    public void delete(Object id) {
        ProductsEntity product = productsDAO.find(id);
        Collection<ParametersEntity> parameters = product.getParametersesById();
        for(ParametersEntity parameter : parameters){
            parametersDAO.delete(parameter.getId());
        }
        productsDAO.delete(id);
    }

    public ProductsEntity find(Object id) {
        return productsDAO.find(id);
    }

    public ProductsEntity update(ProductsEntity productsEntity) {
        return productsDAO.update(productsEntity);
    }

    public ProductsEntity createWithParams(String name, String currentprice, String size, String weight,
                                           String description, HashMap<AttributesEntity, String> attributesAndValues,
                                           int categoryId) {

        ProductsEntity newProduct = new ProductsEntity(name, currentprice, size, weight, description,
                categoriesDAO.getCategoryByID(categoryId));
        ArrayList<ParametersEntity> parametersEntities = new ArrayList<ParametersEntity>();
        for (Map.Entry<AttributesEntity, String> entry : attributesAndValues.entrySet()) {
            parametersEntities.add(new ParametersEntity(entry.getValue(), newProduct, entry.getKey()));
        }
        newProduct.setParametersesById(parametersEntities);
        newProduct = productsDAO.create(newProduct);
        for (ParametersEntity parametersEntity : parametersEntities) {
            parametersDAO.create(parametersEntity);
        }
        StorageEntity storage = new StorageEntity(100, newProduct);
        storageDAO.create(storage);
        return newProduct;
    }

    public int getCategoryId(int id){
        return productsDAO.getProductByID(id).getCategory().getId();
    }

    public String getParameterByAttributeIdProductId(AttributesEntity atr, ProductsEntity prod){
        return parametersDAO.getParameterByAttributeIdProductId(atr, prod).getValue();
    }
}
