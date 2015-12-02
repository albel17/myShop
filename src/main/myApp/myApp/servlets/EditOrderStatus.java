package myApp.servlets;

import myApp.DAO.OrdersDAO;
import myApp.entity.OrdersEntity;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditOrderStatus extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String newStatus = req.getParameter("status");
        OrdersEntity order = new OrdersDAO().getOrderByID(Integer.parseInt(req.getParameter("orderid")));
        order.setOrderStatus(newStatus);
        new OrdersDAO().update(order);
        RequestDispatcher rd = req.getRequestDispatcher("/admin/allorders");
        rd.forward(req, resp);
    }
}
