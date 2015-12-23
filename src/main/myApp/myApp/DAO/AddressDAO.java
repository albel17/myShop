package myApp.DAO;

import myApp.entity.AddressesEntity;
import myApp.entity.PersonsEntity;
import org.springframework.stereotype.Component;

import javax.persistence.Query;

@Component
public class AddressDAO extends GenericDaoJpaImpl<AddressesEntity> {

    public AddressesEntity getAddressByID(int id){
        Query query = em.createQuery("SELECT c FROM AddressesEntity c where c.id=:id");
        query.setParameter("id", id);
        AddressesEntity result = (AddressesEntity) query.getSingleResult();
        return result;
    }

    public void deleteAddressByID(int id){
        delete(id);
    }
}
