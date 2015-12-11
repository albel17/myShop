package myApp.services;

import myApp.DAO.CategoriesDAO;
import myApp.entity.AttributesEntity;
import myApp.entity.CategoriesEntity;
import myApp.entity.ProductsEntity;

import java.util.ArrayList;
import java.util.Collection;

public class CategoriesManager implements GenericManager<CategoriesEntity> {
    private CategoriesDAO categoriesDAO = new CategoriesDAO();

    public ArrayList<CategoriesEntity> getAll() {
        return categoriesDAO.getAll();
    }

    public CategoriesEntity create(CategoriesEntity categoriesEntity) {
        return categoriesDAO.create(categoriesEntity);
    }

    public CategoriesEntity createByNameAndDescription(String name, String description) {
        return create(new CategoriesEntity(name, description));
    }

    public void delete(Object id) {
        categoriesDAO.delete(id);
    }

    public CategoriesEntity find(Object id) {
        return categoriesDAO.find(id);
    }

    public CategoriesEntity update(CategoriesEntity categoriesEntity) {
        return categoriesDAO.update(categoriesEntity);
    }

    public Collection<AttributesEntity> getAttributesById(int id){
        return categoriesDAO.getCategoryByID(id).getAttributes();
    }

    public Collection<ProductsEntity> getProductsById(int id){
        return categoriesDAO.find(id).getProducts();
    }
}
