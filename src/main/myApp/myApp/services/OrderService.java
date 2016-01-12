package myApp.services;

import myApp.DAO.*;
import myApp.bin.Cart;
import myApp.bin.CartItem;
import myApp.entity.OrderItemEntity;
import myApp.entity.OrdersEntity;
import myApp.entity.StorageEntity;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.sql.Date;

@Service
public class OrderService implements GenericService<OrdersEntity> {

    @Resource
    private OrdersDAO ordersDAO;

    @Resource
    private PersonsDAO personsDAO;

    @Resource
    private AddressDAO addressDAO;

    @Resource
    private OrderItemDAO orderItemDAO;

    @Resource
    private StorageDAO storageDAO;

    private final Logger logger = Logger.getLogger(OrderService.class);

    @Override
    public OrdersEntity create(OrdersEntity ordersEntity) {
        ordersEntity = ordersDAO.create(ordersEntity);
        logger.info("Order " + ordersEntity.getId() + " created.");
        return ordersEntity;
    }

    @Override
    public void delete(Object id) {
        logger.info("Order " + id + " deleted.");
        ordersDAO.delete(id);
    }

    @Override
    public OrdersEntity find(Object id) {
        return ordersDAO.find(id);
    }

    @Override
    public OrdersEntity update(OrdersEntity ordersEntity) {
        logger.info("Order " + ordersEntity.getId() + " updated.");
        return ordersDAO.update(ordersEntity);
    }

    public Collection<OrdersEntity> getAll() {
        return ordersDAO.getAllOrders();
    }

    //change order status
    public OrdersEntity changeStatus(int orderId, String newStatus) {
        OrdersEntity order = ordersDAO.find(orderId);
        logger.info("Order " + orderId + " status changed from " + order.getOrderStatus() + " to " + newStatus);
        order.setOrderStatus(newStatus);
        return ordersDAO.update(order);
    }

    public void createWithParams(String paymentmethod, String deliverymethod, Date deliverydate, Cart cart,
                                 int userId, int addressId) {
        OrdersEntity order;
        if (addressId == -1) {
            order = ordersDAO.create(new OrdersEntity(paymentmethod, deliverymethod, "shipped",
                    new Date(new java.util.Date().getTime()), deliverydate, 0, personsDAO.find(userId), null));
        } else {
            order = ordersDAO.create(new OrdersEntity(paymentmethod, deliverymethod, "shipped",
                    new Date(new java.util.Date().getTime()), deliverydate, 0, personsDAO.find(userId),
                    addressDAO.find(addressId)));
        }
        ArrayList<CartItem> items = cart.getItems();
        StorageEntity storage;
        for (CartItem item : items) {
            OrderItemEntity orderItemEntity = new OrderItemEntity(item.getAmount(),
                    item.getProduct().getCurrentPrice(), order, item.getProduct());
            storage = item.getProduct().getStoragesById();
            storage.setAmount(item.getProduct().getStoragesById().getAmount() - item.getAmount());
            storageDAO.update(storage);
            orderItemDAO.create(orderItemEntity);
            order.setCost(order.getCost() + orderItemEntity.getAmount() * orderItemEntity.getPrice());
        }
        if (paymentmethod.equals("card")) {
            order.setOrderStatus("payed");
        }
        logger.info("Order "+order.getId()+" created");
        ordersDAO.update(order);
    }
}
