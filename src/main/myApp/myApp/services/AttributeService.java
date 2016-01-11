package myApp.services;

import myApp.DAO.AttributesDAO;
import myApp.entity.AttributesEntity;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AttributeService implements GenericService<AttributesEntity> {

    @Resource
    private AttributesDAO attributesDAO;

    private final Logger logger = Logger.getLogger(AddressService.class);

    @Override
    public AttributesEntity create(AttributesEntity attributesEntity) {
        logger.info("Attribute " + attributesEntity.getName() + " created.");
        return attributesDAO.create(attributesEntity);
    }

    @Override
    public void delete(Object id) {
        logger.info("Attribute " + attributesDAO.find(id) + " deleted.");
        attributesDAO.delete(id);
    }

    @Override
    public AttributesEntity find(Object id) {
        return attributesDAO.find(id);
    }

    @Override
    public AttributesEntity update(AttributesEntity attributesEntity) {
        logger.info("Attribute " + attributesEntity.getName() + " updated.");
        return attributesDAO.update(attributesEntity);
    }
}
