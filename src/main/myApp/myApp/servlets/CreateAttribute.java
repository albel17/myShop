package myApp.servlets;

import myApp.DAO.AttributesDAO;
import myApp.DAO.CategoriesDAO;
import myApp.entity.AttributesEntity;
import myApp.entity.CategoriesEntity;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class CreateAttribute extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int categoryId = Integer.parseInt(req.getParameter("categoryId"));
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        Collection<CategoriesEntity> categories = new ArrayList<CategoriesEntity>();
        Collection<AttributesEntity> attributes = new ArrayList<AttributesEntity>();
        categories.add(new CategoriesDAO().getCategoryByID(categoryId));
        AttributesEntity attribute = new AttributesEntity(name, description, categories);
        attribute = new AttributesDAO().create(attribute);
        CategoriesEntity category = new CategoriesDAO().getCategoryByID(categoryId);
        attributes.add(attribute);
        category.setAttributes(attributes);
        new CategoriesDAO().update(category);
        RequestDispatcher rd = req.getRequestDispatcher("/admin/editcategory.jsp?id="+categoryId);
        rd.forward(req, resp);
    }
}
