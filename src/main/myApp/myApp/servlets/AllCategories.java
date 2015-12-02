package myApp.servlets;

import myApp.DAO.CategoriesDAO;
import myApp.entity.CategoriesEntity;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


public class AllCategories extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<CategoriesEntity> categories = new CategoriesDAO().getAll();
        req.setAttribute("categories", categories);
        RequestDispatcher rd = req.getRequestDispatcher("/admin/allcategories.jsp");
        rd.forward(req,resp);
    }
}
