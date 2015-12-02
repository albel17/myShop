package myApp.servlets;

import myApp.DAO.CategoriesDAO;
import myApp.entity.AttributesEntity;
import myApp.entity.CategoriesEntity;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class EditCategory extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CategoriesEntity category = new CategoriesDAO().getCategoryByID(Integer.parseInt(req.getParameter("id")));
        Collection<AttributesEntity> attributes = category.getAttributes();
        req.setAttribute("attributes", attributes);
        RequestDispatcher rd = req.getRequestDispatcher("/admin/editcategory.jsp");
        rd.forward(req, resp);
    }
}
