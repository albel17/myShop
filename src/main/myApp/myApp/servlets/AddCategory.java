package myApp.servlets;

import myApp.DAO.CategoriesDAO;
import myApp.entity.CategoriesEntity;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddCategory extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        CategoriesEntity category = new CategoriesEntity(name, description);
        new CategoriesDAO().create(category);

        RequestDispatcher rd = req.getRequestDispatcher("/allcategories");
        rd.forward(req, resp);
    }
}
