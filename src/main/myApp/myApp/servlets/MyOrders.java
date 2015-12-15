package myApp.servlets;

import myApp.services.PersonManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyOrders extends HttpServlet {
    private PersonManager personManager = new PersonManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("orderslist", personManager.getOrders((Integer) req.getSession().getAttribute("userID")));
        RequestDispatcher rd = req.getRequestDispatcher("/myorders.jsp");
        rd.forward(req,resp);
    }
}
