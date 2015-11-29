package myApp.DAO;

import myApp.entity.OrdersEntity;
import myApp.entity.PersonsEntity;

import javax.persistence.Query;
import java.util.ArrayList;

public class OrdersDAO extends GenericDaoJpaImpl<OrdersEntity> {

    public ArrayList<OrdersEntity> getAllOrders(){
        Query query = em.createQuery("SELECT c FROM OrdersEntity c");
        return (ArrayList<OrdersEntity>) query.getResultList();
    }

    public OrdersEntity getOrderByID(int id){
        Query query = em.createQuery("SELECT c FROM OrdersEntity c where c.id=:id");
        query.setParameter("id", id);
        OrdersEntity result = (OrdersEntity) query.getSingleResult();
        return result;
    }
}
