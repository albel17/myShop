package myApp.services;

import myApp.DAO.PersonsDAO;
import myApp.entity.OrdersEntity;
import myApp.entity.PersonsEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.Collection;

@Service
public class PersonManager implements GenericManager<PersonsEntity> {

    @Resource
    private PersonsDAO personsDAO;

    public PersonsEntity create(PersonsEntity personsEntity) {
        return personsDAO.create(personsEntity);
    }

    public void delete(Object id) {
        personsDAO.delete(id);
    }

    public PersonsEntity find(Object id) {
        return personsDAO.find(id);
    }

    public PersonsEntity update(PersonsEntity personsEntity) {
        PersonsEntity person = personsDAO.getPersonByEmail(personsEntity.getEmail());
        person.setName(personsEntity.getName());
        person.setSurname(personsEntity.getSurname());
        person.setBirthdate(personsEntity.getBirthdate());
        person.setEmail(personsEntity.getEmail());
        person.setPassword(personsEntity.getPassword());
        return personsDAO.update(person);
    }

    public int createWithParams(String name, String surname, Date birthdate, String email, String password) {
        PersonsEntity person = personsDAO.create(new PersonsEntity(name, surname, birthdate, email, password, 1));
        return person.getId();
    }

    public Collection<OrdersEntity> getOrders(int userId) {
        return personsDAO.find(userId).getOrdersesById();
    }

    public PersonsEntity getPersonByEmail(String email){
        return personsDAO.getPersonByEmail(email);
    }
}
