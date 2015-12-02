package myApp.servlets;

import myApp.DAO.OrdersDAO;
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

public class AllOrders extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Collection<OrdersEntity> orderslist = new OrdersDAO().getAllOrders();
        req.setAttribute("orderslist", orderslist);
        RequestDispatcher rd = req.getRequestDispatcher("/admin/allorders.jsp");
        rd.forward(req,resp);
    }
}
