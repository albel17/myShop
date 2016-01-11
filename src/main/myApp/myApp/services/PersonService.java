package myApp.services;

import myApp.DAO.PersonsDAO;
import myApp.entity.OrdersEntity;
import myApp.entity.PersonsEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.*;

@Service
public class PersonService implements GenericService<PersonsEntity> {

    @Resource
    private PersonsDAO personsDAO;

    @Override
    public PersonsEntity create(PersonsEntity personsEntity) {
        return personsDAO.create(personsEntity);
    }

    @Override
    public void delete(Object id) {
        personsDAO.delete(id);
    }

    @Override
    public PersonsEntity find(Object id) {
        return personsDAO.find(id);
    }

    @Override
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

    public PersonsEntity getPersonByEmail(String email) {
        return personsDAO.getPersonByEmail(email);
    }

    public boolean hasPerson(String email) {
        if (personsDAO.getPersonCollectionByEmail(email).isEmpty())
            return false;
        else
            return true;
    }

    public List<PersonsEntity> getTopCustomers() {
        List<PersonsEntity> allUsers = personsDAO.getAll();
        Collections.sort(allUsers, new Comparator<PersonsEntity>() {
            public int compare(PersonsEntity o1, PersonsEntity o2) {
                int sum1 = getUsersMoney(o1);
                int sum2 = getUsersMoney(o2);
                if (sum1 == sum2)
                    return 0;
                else
                    return sum1 < sum2 ? 1 : -1;
            }
        });
        if (allUsers.size() > 10)
            return allUsers.subList(0, 10);
        else
            return allUsers;
    }

    public int getUsersMoney(PersonsEntity person) {
        int sum = 0;
        for (OrdersEntity ordersEntity : person.getOrdersesById()) {
            sum += ordersEntity.getCost();
        }
        return sum;
    }
}
