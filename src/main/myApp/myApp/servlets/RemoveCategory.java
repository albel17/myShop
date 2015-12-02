package myApp.servlets;

import myApp.DAO.AddressDAO;
import myApp.DAO.CategoriesDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RemoveCategory extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        new CategoriesDAO().delete(id);
        RequestDispatcher rd = req.getRequestDispatcher("/admin/allcategories");
        rd.forward(req,resp);
    }
}
