package myApp.DAO;

import myApp.entity.PersonsEntity;
import myApp.entity.ProductsEntity;

import javax.persistence.Query;
import java.util.ArrayList;

public class PersonsDAO extends GenericDaoJpaImpl<PersonsEntity> {

    public PersonsEntity getPersonByEmail(String email){
        Query query = em.createQuery("SELECT c FROM PersonsEntity c where c.email=:email");
        query.setParameter("email", email);
        PersonsEntity result = (PersonsEntity) query.getSingleResult();
        return result;
    }

    public PersonsEntity getPersonByID(int id){
        Query query = em.createQuery("SELECT c FROM PersonsEntity c where c.id=:id");
        query.setParameter("id", id);
        PersonsEntity result = (PersonsEntity) query.getSingleResult();
        return result;
    }

}
