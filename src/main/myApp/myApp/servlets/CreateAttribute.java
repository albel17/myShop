package myApp.servlets;

import myApp.services.CategoriesManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateAttribute extends HttpServlet {
    private CategoriesManager categoriesManager = new CategoriesManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        categoriesManager.createAttribute(Integer.parseInt(req.getParameter("categoryId")), req.getParameter("name"),
                req.getParameter("description"));
        RequestDispatcher rd = req.getRequestDispatcher("/admin/editcategory.jsp?id=" + req.getParameter("categoryId"));
        rd.forward(req, resp);
    }
}
