package myApp.services;

import myApp.DAO.AddressDAO;
import myApp.DAO.OrderItemDAO;
import myApp.DAO.OrdersDAO;
import myApp.DAO.PersonsDAO;
import myApp.bin.Cart;
import myApp.bin.CartItem;
import myApp.entity.OrderItemEntity;
import myApp.entity.OrdersEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

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

    public Collection<OrdersEntity> getAll() {
        return ordersDAO.getAllOrders();
    }

    public OrdersEntity changeStatus(int orderId, String newStatus) {
        OrdersEntity order = ordersDAO.find(orderId);
        order.setOrderStatus(newStatus);
        return ordersDAO.update(order);
    }

    public void createWithParams(String paymentmethod, String deliverymethod, String date, Cart cart, int userId, int addressId) {
        OrdersEntity order = ordersDAO.create(new OrdersEntity(paymentmethod, deliverymethod, "created",
                new Date().toString(), date, 0, new PersonsDAO().find(userId), new AddressDAO().find(addressId)));
        ArrayList<CartItem> items = cart.getItems();
        OrderItemDAO orderItemDAO = new OrderItemDAO();
        for (CartItem item : items) {
            OrderItemEntity orderItemEntity = new OrderItemEntity(item.getAmount(),
                    Integer.parseInt(item.getProduct().getCurrentPrice()), order, item.getProduct());
            orderItemDAO.create(orderItemEntity);
            order.setCost(order.getCost() + orderItemEntity.getAmount() * orderItemEntity.getPrice());
        }
        ordersDAO.update(order);
    }
}
