package myApp.servlets;

import myApp.bin.Cart;
import myApp.services.OrderManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateOrder extends HttpServlet {
    private OrderManager orderManager = new OrderManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        orderManager.createWithParams((String) req.getSession().getAttribute("paymentmethod"),
                (String) req.getSession().getAttribute("deliverymethod"), req.getParameter("date"),
                (Cart) req.getSession().getAttribute("cart"), (Integer) req.getSession().getAttribute("userID"),
                Integer.parseInt(req.getParameter("address")));
        req.getSession().setAttribute("cart", null);
        RequestDispatcher rd = req.getRequestDispatcher("/profile");
        rd.forward(req, resp);
    }
}
