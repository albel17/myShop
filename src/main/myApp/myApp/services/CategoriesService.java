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

    private final Logger logger = Logger.getLogger(CategoriesService.class);

    public ArrayList<CategoriesEntity> getAll() {
        return categoriesDAO.getAll();
    }

    @Override
    public CategoriesEntity create(CategoriesEntity categoriesEntity) {
        logger.info("Category " + categoriesEntity.getName() + " created.");
        return categoriesDAO.create(categoriesEntity);
    }

    public CategoriesEntity createByNameAndDescription(String name, String description) {

        logger.info("Category " + name + " created.");
        return create(new CategoriesEntity(name, description));
    }

    @Override
    public void delete(Object id) {

        logger.info("Category " + categoriesDAO.find(id).getName() + " deleted.");
        categoriesDAO.delete(id);
    }

    @Override
    public CategoriesEntity find(Object id) {
        return categoriesDAO.find(id);
    }

    @Override
    public CategoriesEntity update(CategoriesEntity categoriesEntity) {
        logger.info("Category " + categoriesEntity.getName() + " updated.");
        return categoriesDAO.update(categoriesEntity);
    }

    //get all attributes by category id
    public Collection<AttributesEntity> getAttributesById(int id) {
        return categoriesDAO.getCategoryByID(id).getAttributes();
    }

    //get all products by category id
    public Collection<ProductsEntity> getProductsById(int id) {
        return categoriesDAO.find(id).getProducts();
    }

    public void createAttribute(int categoryId, String name, String description) {
        Collection<CategoriesEntity> categories = new ArrayList<>();
        Collection<AttributesEntity> attributes = new ArrayList<>();
        categories.add(categoriesDAO.find(categoryId));
        AttributesEntity attribute = attributesDAO.create(new AttributesEntity(name, description, categories));
        CategoriesEntity category = categoriesDAO.find(categoryId);
        attributes.add(attribute);
        attributes.addAll(category.getAttributes());
        category.setAttributes(attributes);
        categoriesDAO.update(category);
    }

    //return true if category exist
    public boolean hasCategory(String name) {
        if (categoriesDAO.getCategoryCollectionByName(name).isEmpty())
            return false;
        else
            return true;
    }
}
