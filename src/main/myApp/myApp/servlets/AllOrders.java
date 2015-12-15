package myApp.servlets;

import myApp.services.OrderManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AllOrders extends HttpServlet {
    private OrderManager orderManager = new OrderManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("orderslist", orderManager.getAll());
        RequestDispatcher rd = req.getRequestDispatcher("/admin/allorders.jsp");
        rd.forward(req,resp);
    }
}
