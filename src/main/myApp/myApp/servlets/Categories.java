package myApp.servlets;

import myApp.DAO.PersonsDAO;
import myApp.entity.CategoriesEntity;
import myApp.DAO.CategoriesDAO;
import myApp.entity.PersonsEntity;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class Categories extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req,resp);
    }

    public void process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        ArrayList<CategoriesEntity> categories = new CategoriesDAO().getAll();
        req.setAttribute("categories", categories);
        RequestDispatcher rd = req.getRequestDispatcher("/index.jsp");
        rd.forward(req,resp);
    }
}
