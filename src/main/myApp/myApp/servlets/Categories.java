package myApp.servlets;

import myApp.DAO.CategoriesDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Admin on 25.11.15.
 */
public class Categories extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        ArrayList<String> categories = new CategoriesDAO().getAll();
        System.out.println(categories);
        req.setAttribute("categories", categories);
        req.setAttribute("test", "HI!");
        ServletContext ct = getServletContext();
        RequestDispatcher rd = req.getRequestDispatcher("/index.jsp");
        rd.forward(req,resp);
    }
}
