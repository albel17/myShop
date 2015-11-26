package myApp.servlets;

import myApp.DAO.ProductsDAO;
import myApp.entity.ProductsEntity;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Admin on 26.11.15.
 */
public class ProductDescription extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductsDAO dao = new ProductsDAO();
        ArrayList<String> productInfo = dao.getProductInfoByID(Integer.parseInt(req.getParameter("id")));
        req.setAttribute("productInfo", productInfo);
        ProductsEntity product = dao.getProductByID(Integer.parseInt(req.getParameter("id")));
        req.setAttribute("product", product);
        RequestDispatcher rd = req.getRequestDispatcher("/productDescription.jsp");
        rd.forward(req, resp);
    }
}
