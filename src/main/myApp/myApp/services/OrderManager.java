package myApp.services;

import myApp.DAO.AddressDAO;
import myApp.DAO.OrderItemDAO;
import myApp.DAO.OrdersDAO;
import myApp.DAO.PersonsDAO;
import myApp.bin.Cart;
import myApp.bin.CartItem;
import myApp.entity.OrderItemEntity;
import myApp.entity.OrdersEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.sql.Date;

@Service
public class OrderManager implements GenericManager<OrdersEntity> {

    @Resource
    private OrdersDAO ordersDAO;

    @Resource
    private PersonsDAO personsDAO;

    @Resource
    private AddressDAO addressDAO;

    @Resource
    private OrderItemDAO orderItemDAO;

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

    public void createWithParams(String paymentmethod, String deliverymethod, Date deliverydate, Cart cart,
                                 int userId, int addressId) {
        OrdersEntity order;
        if (addressId == -1) {
            order = ordersDAO.create(new OrdersEntity(paymentmethod, deliverymethod, "created",
                    new Date(new java.util.Date().getTime()), deliverydate, 0, personsDAO.find(userId), null));
        } else {
            order = ordersDAO.create(new OrdersEntity(paymentmethod, deliverymethod, "created",
                    new Date(new java.util.Date().getTime()), deliverydate, 0, personsDAO.find(userId),
                    addressDAO.find(addressId)));
        }
        ArrayList<CartItem> items = cart.getItems();
        for (CartItem item : items) {
            OrderItemEntity orderItemEntity = new OrderItemEntity(item.getAmount(),
                    Integer.parseInt(item.getProduct().getCurrentPrice()), order, item.getProduct());
            orderItemDAO.create(orderItemEntity);
            order.setCost(order.getCost() + orderItemEntity.getAmount() * orderItemEntity.getPrice());
        }
        ordersDAO.update(order);
    }
}
