package myApp.DAO;

import myApp.entity.OrdersEntity;
import myApp.entity.PersonsEntity;
import org.springframework.stereotype.Component;

import javax.persistence.Query;
import java.util.ArrayList;

@Component
public class OrdersDAO extends GenericDaoJpaImpl<OrdersEntity> {

    public ArrayList<OrdersEntity> getAllOrders(){
        Query query = em.createQuery("SELECT c FROM OrdersEntity c");
        return (ArrayList<OrdersEntity>) query.getResultList();
    }
}
