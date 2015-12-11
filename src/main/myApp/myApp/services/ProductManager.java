package myApp.services;

import myApp.DAO.CategoriesDAO;
import myApp.DAO.ParametersDAO;
import myApp.DAO.ProductsDAO;
import myApp.entity.AttributesEntity;
import myApp.entity.ParametersEntity;
import myApp.entity.ProductsEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProductManager implements GenericManager<ProductsEntity> {
    private ProductsDAO productsDAO = new ProductsDAO();

    public ProductsEntity create(ProductsEntity productsEntity) {
        return productsDAO.create(productsEntity);
    }

    public void delete(Object id) {
        productsDAO.delete(id);
    }

    public ProductsEntity find(Object id) {
        return productsDAO.find(id);
    }

    public ProductsEntity update(ProductsEntity productsEntity) {
        return productsDAO.update(productsEntity);
    }

    public ProductsEntity createWithParams(String name, String currentprice, String size, String weight,
                                           String description, HashMap<AttributesEntity, String> attributesAndValues, int categoryId) {

        ProductsEntity newProduct = new ProductsEntity(name, currentprice, size, weight, description,
                new CategoriesDAO().getCategoryByID(categoryId));
        ArrayList<ParametersEntity> parametersEntities = new ArrayList<ParametersEntity>();
        for (Map.Entry<AttributesEntity, String> entry : attributesAndValues.entrySet()) {
            parametersEntities.add(new ParametersEntity(entry.getValue(), newProduct, entry.getKey()));
        }
        newProduct.setParametersesById(parametersEntities);
        newProduct = productsDAO.create(newProduct);
        ParametersDAO parametersDAO = new ParametersDAO();
        for(ParametersEntity parametersEntity : parametersEntities){
            parametersDAO.create(parametersEntity);
        }

        return newProduct;
    }
}
