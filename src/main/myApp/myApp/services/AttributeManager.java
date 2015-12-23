package myApp.services;

import myApp.DAO.AttributesDAO;
import myApp.entity.AttributesEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AttributeManager implements GenericManager<AttributesEntity> {

    @Resource
    private AttributesDAO attributesDAO;

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
