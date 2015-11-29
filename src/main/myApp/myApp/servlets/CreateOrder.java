package myApp.servlets;

import myApp.DAO.AddressDAO;
import myApp.DAO.OrderItemDAO;
import myApp.DAO.OrdersDAO;
import myApp.DAO.PersonsDAO;
import myApp.bin.Cart;
import myApp.bin.CartItem;
import myApp.entity.AddressesEntity;
import myApp.entity.OrderItemEntity;
import myApp.entity.OrdersEntity;
import myApp.entity.PersonsEntity;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class CreateOrder extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String paymentmethod = (String) req.getSession().getAttribute("paymentmethod");
        String deliverymethod = (String) req.getSession().getAttribute("deliverymethod");
        String date = req.getParameter("date");
        Cart cart = (Cart)req.getSession().getAttribute("cart");
        String currentDate = new Date().toString();
        PersonsEntity person = new PersonsDAO().getPersonByID((Integer) req.getSession().getAttribute("userID"));
        AddressesEntity address = new AddressDAO().getAddressByID(Integer.parseInt(req.getParameter("address")));
        OrdersEntity order = new OrdersEntity(paymentmethod, deliverymethod, "created", currentDate, date, 0, person, address);
        order = new OrdersDAO().create(order);
        int orderId = order.getId();
        ArrayList<CartItem> items = cart.getItems();
        OrderItemDAO orderItemDAO = new OrderItemDAO();
        for(CartItem item : items){
            OrderItemEntity orderItemEntity = new OrderItemEntity(item.getAmount(), Integer.parseInt(item.getProduct().getCurrentPrice()),order,item.getProduct());
            orderItemDAO.create(orderItemEntity);
            order.setCost(order.getCost()+orderItemEntity.getAmount()*orderItemEntity.getPrice());
        }
        new OrdersDAO().update(order);
        req.getSession().setAttribute("cart", null);
        RequestDispatcher rd = req.getRequestDispatcher("/profile");
        rd.forward(req,resp);
    }
}
