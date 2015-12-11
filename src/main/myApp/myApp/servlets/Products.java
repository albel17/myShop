package myApp.servlets;

import myApp.services.CategoriesManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Products extends HttpServlet {
    private CategoriesManager categoriesManager = new CategoriesManager();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("products", categoriesManager.getProductsById(Integer.parseInt(req.getParameter("id"))));
        RequestDispatcher rd = req.getRequestDispatcher("/products.jsp");
        rd.forward(req, resp);
    }


}
