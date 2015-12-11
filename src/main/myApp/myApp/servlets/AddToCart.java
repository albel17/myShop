package myApp.servlets;

import myApp.bin.Cart;
import myApp.bin.CartItem;
import myApp.entity.ProductsEntity;
import myApp.services.ProductManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddToCart extends HttpServlet {
    private ProductManager productManager = new ProductManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductsEntity product = productManager.find(Integer.parseInt(req.getParameter("id")));
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
        }
        cart.add(new CartItem(product, 1));
        req.getSession().setAttribute("cart", cart);
        RequestDispatcher rd = req.getRequestDispatcher("/productDescription?id=" + req.getParameter("id"));
        rd.forward(req, resp);
    }
}
