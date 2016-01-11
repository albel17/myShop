package myApp.DAO;

import myApp.entity.PersonsEntity;
import org.springframework.stereotype.Component;

import javax.persistence.Query;
import java.util.Collection;
import java.util.List;

@Component
public class PersonsDAO extends GenericDaoJpaImpl<PersonsEntity> {

    public PersonsEntity getPersonByEmail(String email){
        Query query = em.createQuery("SELECT c FROM PersonsEntity c where c.email=:email");
        query.setParameter("email", email);
        return (PersonsEntity) query.getSingleResult();
    }

    public Collection<PersonsEntity> getPersonCollectionByEmail(String email){
        Query query = em.createQuery("SELECT c FROM PersonsEntity c where c.email=:email");
        query.setParameter("email", email);
        return query.getResultList();
    }

    public PersonsEntity getPersonByID(int id){
        Query query = em.createQuery("SELECT c FROM PersonsEntity c where c.id=:id");
        query.setParameter("id", id);
        return (PersonsEntity) query.getSingleResult();
    }

    public List<PersonsEntity> getAll(){
        Query query = em.createQuery("SELECT c FROM PersonsEntity c");
        return query.getResultList();
    }

}
