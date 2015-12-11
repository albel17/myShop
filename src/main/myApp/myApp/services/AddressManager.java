package myApp.services;

import myApp.DAO.AddressDAO;
import myApp.DAO.PersonsDAO;
import myApp.entity.AddressesEntity;
import myApp.entity.PersonsEntity;

import java.util.Collection;

public class AddressManager implements GenericManager<AddressesEntity> {
    private AddressDAO addressDAO = new AddressDAO();

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
        return create(new AddressesEntity(country, city, postalcode, street, house, flat, new PersonsDAO().getPersonByID(userId)));
    }

    public Collection<AddressesEntity> getAddressListByUserId(int userId) {
        return new PersonsDAO().getPersonByID(userId).getAddressesById();
    }
}
