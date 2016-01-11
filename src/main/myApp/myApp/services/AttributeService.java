package myApp.services;

import myApp.DAO.AttributesDAO;
import myApp.entity.AttributesEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AttributeService implements GenericService<AttributesEntity> {

    @Resource
    private AttributesDAO attributesDAO;

    @Override
    public AttributesEntity create(AttributesEntity attributesEntity) {
        return attributesDAO.create(attributesEntity);
    }

    @Override
    public void delete(Object id) {
        attributesDAO.delete(id );
    }

    @Override
    public AttributesEntity find(Object id) {
        return attributesDAO.find(id);
    }

    @Override
    public AttributesEntity update(AttributesEntity attributesEntity) {
        return attributesDAO.update(attributesEntity);
    }
}
