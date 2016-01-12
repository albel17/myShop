package myApp.services;

import myApp.DAO.AddressDAO;
import myApp.DAO.PersonsDAO;
import myApp.entity.AddressesEntity;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;

@Service
public class AddressService implements GenericService<AddressesEntity> {

    @Resource
    private AddressDAO addressDAO;

    @Resource
    private PersonsDAO personsDAO;

    private final Logger logger = Logger.getLogger(AddressService.class);

    @Override
    public AddressesEntity create(AddressesEntity addressesEntity) {
        AddressesEntity addressesEntity1 = addressDAO.create(addressesEntity);
        logger.info("New address created by " + addressesEntity1.getPersonsByClientId().getEmail());
        return addressDAO.create(addressesEntity);
    }

    @Override
    public void delete(Object id) {
        logger.info("Address deleted by " + addressDAO.find(id).getPersonsByClientId().getEmail());
        addressDAO.delete(id);
    }

    @Override
    public AddressesEntity find(Object id) {
        return addressDAO.find(id);
    }

    @Override
    public AddressesEntity update(AddressesEntity addressesEntity) {
        logger.info("Address updated by " + addressesEntity.getPersonsByClientId().getEmail());
        return addressDAO.update(addressesEntity);
    }

    //Create Address in BD with all params
    public AddressesEntity createWithParams(String country, String city, String postalcode, String street, String house,
                                            String flat, int userId) {
        logger.info("Address created by " + personsDAO.getPersonByID(userId).getEmail());
        return create(new AddressesEntity(country, city, postalcode, street, house, flat, personsDAO.getPersonByID(userId)));
    }

    //Return collection of 1 element, can be empty if nothing found
    public Collection<AddressesEntity> getAddressListByUserId(int userId) {
        return personsDAO.getPersonByID(userId).getAddressesById();
    }
}
