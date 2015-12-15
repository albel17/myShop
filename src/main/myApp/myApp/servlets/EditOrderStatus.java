package myApp.servlets;

import myApp.services.OrderManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditOrderStatus extends HttpServlet {
    private OrderManager orderManager = new OrderManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        orderManager.changeStatus(Integer.parseInt(req.getParameter("orderid")), req.getParameter("status"));
        RequestDispatcher rd = req.getRequestDispatcher("/admin/allorders");
        rd.forward(req, resp);
    }
}
