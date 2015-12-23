package myApp.services;

import myApp.DAO.AddressDAO;
import myApp.DAO.PersonsDAO;
import myApp.entity.AddressesEntity;
import myApp.entity.PersonsEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;

@Service
public class AddressManager implements GenericManager<AddressesEntity> {

    @Resource
    private AddressDAO addressDAO;

    @Resource
    private PersonsDAO personsDAO;

    public AddressesEntity create(AddressesEntity addressesEntity) {
        return addressDAO.create(addressesEntity);
    }

    public void delete(Object id) {
        addressDAO.delete(id);
    }

    public AddressesEntity find(Object id) {
        return addressDAO.find(id);
    }

    public AddressesEntity update(AddressesEntity addressesEntity) {
        return addressDAO.update(addressesEntity);
    }

    public AddressesEntity createWithParams(String country, String city, String postalcode, String street, String house, String flat, int userId) {
        return create(new AddressesEntity(country, city, postalcode, street, house, flat, personsDAO.getPersonByID(userId)));
    }

    public Collection<AddressesEntity> getAddressListByUserId(int userId) {
        return personsDAO.getPersonByID(userId).getAddressesById();
    }
}
