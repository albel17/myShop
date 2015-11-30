package myApp.servlets;

import myApp.DAO.CategoriesDAO;
import myApp.DAO.ProductsDAO;
import myApp.entity.AttributesEntity;
import myApp.entity.CategoriesEntity;
import myApp.entity.ProductsEntity;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class EditProducts extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<ProductsEntity> products = new ProductsDAO().getProductsByCategoryID(req.getParameter("id"));
        CategoriesEntity category = new CategoriesDAO().getCategoryByID(Integer.parseInt(req.getParameter("id")));
        Collection<AttributesEntity> attributes = category.getAttributes();
        req.setAttribute("products", products);
        req.setAttribute("attributes", attributes);
        RequestDispatcher rd = req.getRequestDispatcher("/editproducts.jsp");
        rd.forward(req,resp);
    }
}
