package myApp.servlets;

import myApp.entity.CategoriesEntity;
import myApp.services.CategoriesManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class AllProducts extends HttpServlet {
    private CategoriesManager categoriesManager = new CategoriesManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<CategoriesEntity> categories = categoriesManager.getAll();
        req.setAttribute("categories", categories);
        RequestDispatcher rd = req.getRequestDispatcher("/admin/allproducts.jsp");
        rd.forward(req,resp);
    }
}
