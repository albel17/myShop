package myApp.services;

import myApp.DAO.PersonsDAO;
import myApp.entity.OrdersEntity;
import myApp.entity.PersonsEntity;

import java.util.Collection;

public class PersonManager implements GenericManager<PersonsEntity> {
    private PersonsDAO personsDAO = new PersonsDAO();

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
        return personsDAO.update(personsEntity);
    }

    public int createWithParams(String name, String surname, String birthdate, String email, String password) {
        PersonsEntity person = personsDAO.create(new PersonsEntity(name, surname, birthdate, email, password, 1));
        return person.getId();
    }

    public Collection<OrdersEntity> getOrders(int userId) {
        return personsDAO.find(userId).getOrdersesById();
    }
}
