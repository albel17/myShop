package myApp.DAO;

import myApp.entity.AttributesEntity;
import myApp.entity.ParametersEntity;
import myApp.entity.PersonsEntity;
import myApp.entity.ProductsEntity;
import org.springframework.stereotype.Component;

import javax.persistence.Query;

@Component
public class ParametersDAO extends GenericDaoJpaImpl<ParametersEntity> {

    public ParametersEntity getParameterByAttributeIdProductId(AttributesEntity atr, ProductsEntity prod) {
        Query query = em.createQuery("SELECT c FROM ParametersEntity c where c.attribute=:attribute and c.productsByProductId=:product");
        query.setParameter("attribute", atr);
        query.setParameter("product", prod);
        return (ParametersEntity) query.getSingleResult();
    }
}
