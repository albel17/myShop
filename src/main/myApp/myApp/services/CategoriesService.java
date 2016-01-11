package myApp.services;

import myApp.DAO.AttributesDAO;
import myApp.DAO.API.CategoriesDAO;
import myApp.entity.AttributesEntity;
import myApp.entity.CategoriesEntity;
import myApp.entity.ProductsEntity;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;

@Service
public class CategoriesService implements GenericService<CategoriesEntity> {
    @Resource
    private CategoriesDAO categoriesDAO;

    @Resource
    private AttributesDAO attributesDAO;

    public ArrayList<CategoriesEntity> getAll() {
        return categoriesDAO.getAll();
    }

    @Override
    public CategoriesEntity create(CategoriesEntity categoriesEntity) {
        return categoriesDAO.create(categoriesEntity);
    }

    public CategoriesEntity createByNameAndDescription(String name, String description) {
        return create(new CategoriesEntity(name, description));
    }
    @Override
    public void delete(Object id) {
        categoriesDAO.delete(id);
    }

    @Override
    public CategoriesEntity find(Object id) {
        return categoriesDAO.find(id);
    }

    @Override
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
        Collection<CategoriesEntity> categories = new ArrayList<>();
        Collection<AttributesEntity> attributes = new ArrayList<>();
        categories.add(categoriesDAO.find(id));
        AttributesEntity attribute = attributesDAO.create(new AttributesEntity(name, description, categories));
        CategoriesEntity category = categoriesDAO.find(id);
        attributes.add(attribute);
        attributes.addAll(category.getAttributes());
        category.setAttributes(attributes);
        categoriesDAO.update(category);
    }

    public boolean hasCategory(String name) {
        if (categoriesDAO.getCategoryCollectionByName(name).isEmpty())
            return false;
        else
            return true;
    }
}
