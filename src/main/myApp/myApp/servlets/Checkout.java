package myApp.servlets;

import myApp.bin.Bin;
import myApp.bin.OrderItem;
import myApp.bin.OrderItemCollection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class Checkout extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Bin bin = (Bin) req.getSession().getAttribute("bin");
        RequestDispatcher rd;
        if (bin==null || bin.ifEmpty()){
            rd = req.getRequestDispatcher("/profile");
        }
        else {
            rd = req.getRequestDispatcher("/checkout.jsp");
            OrderItemCollection orderItemCollection = new OrderItemCollection((Bin) req.getSession().getAttribute("bin"));
            ArrayList<OrderItem> orderItems = orderItemCollection.getOrderItems();
            req.setAttribute("orderItems", orderItems);
        }
        rd.forward(req,resp);
    }
}
