package myApp.servlets;

import myApp.services.CategoriesManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddCategory extends HttpServlet {
    private CategoriesManager categoriesManager = new CategoriesManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        categoriesManager.createByNameAndDescription(req.getParameter("name"), req.getParameter("description"));

        RequestDispatcher rd = req.getRequestDispatcher("/admin/allcategories");
        rd.forward(req, resp);
    }
}
