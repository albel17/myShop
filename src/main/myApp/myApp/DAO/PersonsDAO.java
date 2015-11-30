package myApp.DAO;

import myApp.entity.PersonsEntity;

import javax.persistence.Query;

public class PersonsDAO extends GenericDaoJpaImpl<PersonsEntity> {

    public PersonsEntity getPersonByEmail(String email){
        Query query = em.createQuery("SELECT c FROM PersonsEntity c where c.email=:email");
        query.setParameter("email", email);
        return (PersonsEntity) query.getSingleResult();
    }

    public PersonsEntity getPersonByID(int id){
        Query query = em.createQuery("SELECT c FROM PersonsEntity c where c.id=:id");
        query.setParameter("id", id);
        return (PersonsEntity) query.getSingleResult();
    }

}
