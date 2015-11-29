package myApp.servlets;

import myApp.DAO.ProductsDAO;
import myApp.bin.Cart;
import myApp.bin.CartItem;
import myApp.entity.ProductsEntity;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddToCart extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        ProductsEntity product = new ProductsDAO().getProductByID(id);
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
        }
        cart.add(new CartItem(product, 1));
        req.getSession().setAttribute("cart", cart);
        RequestDispatcher rd = req.getRequestDispatcher("/productDescription?id="+id);
        rd.forward(req, resp);
    }
}
