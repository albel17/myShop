package myApp.services;

import myApp.DAO.AttributesDAO;
import myApp.DAO.API.CategoriesDAO;
import myApp.entity.AttributesEntity;
import myApp.entity.CategoriesEntity;
import myApp.entity.ProductsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;

@Service
public class CategoriesManager implements GenericManager<CategoriesEntity> {
    @Resource//(name="categoriesDAOIMPL")
    private CategoriesDAO categoriesDAO;

    @Resource
    private AttributesDAO attributesDAO;

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

    public Collection<AttributesEntity> getAttributesById(int id) {
        return categoriesDAO.getCategoryByID(id).getAttributes();
    }

    public Collection<ProductsEntity> getProductsById(int id) {
        return categoriesDAO.find(id).getProducts();
    }

    public void createAttribute(int id, String name, String description) {
        Collection<CategoriesEntity> categories = new ArrayList<CategoriesEntity>();
        Collection<AttributesEntity> attributes = new ArrayList<AttributesEntity>();
        categories.add(categoriesDAO.find(id));
        AttributesEntity attribute = attributesDAO.create(new AttributesEntity(name, description, categories));
        CategoriesEntity category = categoriesDAO.find(id);
        attributes.add(attribute);
        category.setAttributes(attributes);
        categoriesDAO.update(category);
    }
}
