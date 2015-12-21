package myApp.services;

import myApp.DAO.AttributesDAO;
import myApp.DAO.CategoriesDAO;
import myApp.entity.AttributesEntity;
import myApp.entity.CategoriesEntity;
import myApp.entity.ProductsEntity;
import org.hibernate.service.spi.InjectService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Collection;

@Component
public class CategoriesManager implements GenericManager<CategoriesEntity> {
    //private CategoriesDAO categoriesDAO = new CategoriesDAO();
    @Inject
    private CategoriesDAO categoriesDAO;

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
        AttributesEntity attribute = new AttributesDAO().create(new AttributesEntity(name, description, categories));
        CategoriesEntity category = categoriesDAO.find(id);
        attributes.add(attribute);
        category.setAttributes(attributes);
        categoriesDAO.update(category);
    }
}
