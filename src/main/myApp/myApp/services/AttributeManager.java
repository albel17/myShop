package myApp.services;

import myApp.DAO.AttributesDAO;
import myApp.entity.AttributesEntity;

public class AttributeManager implements GenericManager<AttributesEntity> {
    private AttributesDAO attributesDAO = new AttributesDAO();

    public AttributesEntity create(AttributesEntity attributesEntity) {
        return attributesDAO.create(attributesEntity);
    }

    public void delete(Object id) {
        attributesDAO.delete(id );
    }

    public AttributesEntity find(Object id) {
        return attributesDAO.find(id);
    }

    public AttributesEntity update(AttributesEntity attributesEntity) {
        return attributesDAO.update(attributesEntity);
    }
}
