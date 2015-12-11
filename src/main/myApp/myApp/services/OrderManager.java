package myApp.services;

import myApp.DAO.OrdersDAO;
import myApp.entity.OrdersEntity;

import java.util.Collection;

public class OrderManager implements GenericManager<OrdersEntity> {
    private OrdersDAO ordersDAO = new OrdersDAO();

    public OrdersEntity create(OrdersEntity ordersEntity) {
        return ordersDAO.create(ordersEntity);
    }

    public void delete(Object id) {
ordersDAO.delete(id);
    }

    public OrdersEntity find(Object id) {
        return ordersDAO.find(id);
    }

    public OrdersEntity update(OrdersEntity ordersEntity) {
        return ordersDAO.update(ordersEntity);
    }

    public Collection<OrdersEntity> getAll(){
        return ordersDAO.getAllOrders();
    }
}
