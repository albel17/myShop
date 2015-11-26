package myApp.DAO;

import myApp.entity.PersonsEntity;
import myApp.entity.ProductsEntity;

import javax.persistence.Query;
import java.util.ArrayList;

/**
 * Created by Admin on 24.11.15.
 */
public class PersonsDAO extends GenericDaoJpaImpl<PersonsEntity> {

    public PersonsEntity getPersonByEmail(String email){
        Query query = em.createQuery("SELECT c FROM PersonsEntity c where c.email=:email");
        query.setParameter("email", email);
        PersonsEntity result = (PersonsEntity) query.getSingleResult();
        return result;
    }

}
