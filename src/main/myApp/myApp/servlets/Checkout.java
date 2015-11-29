package myApp.servlets;

import myApp.bin.Cart;
import myApp.bin.CartItem;

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
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        RequestDispatcher rd;
        if (cart == null || cart.ifEmpty()){
            rd = req.getRequestDispatcher("/profile");
        }
        else {
            rd = req.getRequestDispatcher("/checkout.jsp");
            ArrayList<CartItem> cartItems = cart.getItems();
            req.setAttribute("cartItems", cartItems);
            req.setAttribute("sum", cart.getSum());
        }
        rd.forward(req, resp);
    }
}
