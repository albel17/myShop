package myApp.servlets;

import myApp.DAO.CategoriesDAO;
import myApp.DAO.ProductsDAO;
import myApp.ParametersEntity;
import myApp.ProductsEntity;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class Products extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ArrayList<ProductsEntity> products = new ProductsDAO().getProductsByCategoryID(req.getParameter("id"));
        req.setAttribute("products", products);
        RequestDispatcher rd = req.getRequestDispatcher("/products.jsp");
        rd.forward(req,resp);
    }


}
