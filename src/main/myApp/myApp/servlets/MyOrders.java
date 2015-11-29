package myApp.servlets;

import myApp.DAO.PersonsDAO;
import myApp.entity.OrdersEntity;
import myApp.entity.PersonsEntity;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class MyOrders extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PersonsEntity person = new PersonsDAO().getPersonByID((Integer) req.getSession().getAttribute("userID"));
        Collection<OrdersEntity> orderslist = person.getOrdersesById();
        req.setAttribute("orderslist", orderslist);
        RequestDispatcher rd = req.getRequestDispatcher("/myorders.jsp");
        rd.forward(req,resp);
    }
}
