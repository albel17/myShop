package myApp.servlets;

import myApp.entity.AttributesEntity;
import myApp.services.CategoriesManager;
import myApp.services.ProductManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class AddProduct extends HttpServlet {
    private ProductManager productManager = new ProductManager();
    private CategoriesManager categoriesManager = new CategoriesManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HashMap<AttributesEntity, String> attributesAndValues = new HashMap<AttributesEntity, String>(); //creating a map of attributes and their values
        for (AttributesEntity attribute : categoriesManager.find(Integer.parseInt(req.getParameter("categoryId"))).getAttributes()) {
            attributesAndValues.put(attribute, req.getParameter(String.valueOf(attribute.getId())));
        }

//        productManager.createWithParams(req.getParameter("name"), req.getParameter("currentprice"),
//                req.getParameter("size"), req.getParameter("weight"), req.getParameter("description"),
//                attributesAndValues, Integer.parseInt(req.getParameter("categoryId")));

        RequestDispatcher rd = req.getRequestDispatcher("/admin/editproducts?id=" + Integer.parseInt(req.getParameter("categoryId")));
        rd.forward(req, resp);
    }
}
