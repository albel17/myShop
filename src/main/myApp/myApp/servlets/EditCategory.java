package myApp.servlets;

import myApp.services.CategoriesManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditCategory extends HttpServlet {
    private CategoriesManager categoriesManager = new CategoriesManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("attributes", categoriesManager.getAttributesById(Integer.parseInt(req.getParameter("id"))));
        RequestDispatcher rd = req.getRequestDispatcher("/admin/editcategory.jsp");
        rd.forward(req, resp);
    }
}
