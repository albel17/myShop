package myApp.servlets;

import myApp.DAO.ParametersDAO;
import myApp.DAO.ProductsDAO;
import myApp.entity.AttributesEntity;
import myApp.entity.CategoriesEntity;
import myApp.entity.ProductsEntity;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class ProductDescription extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductsDAO dao = new ProductsDAO();
        ProductsEntity product = dao.getProductByID(Integer.parseInt(req.getParameter("id")));
        req.setAttribute("product", product);
        CategoriesEntity category = product.getCategory();
        Collection<AttributesEntity> attributes = category.getAttributes();
        req.setAttribute("attributes", attributes);
        ArrayList<String> values = new ArrayList<String>();
        for (AttributesEntity attribute : attributes) {
            values.add(new ParametersDAO().getParameterByAttributeIdProductId(attribute, product).getValue());
        }
        req.setAttribute("values", values);
        req.setAttribute("categoryId", category.getId());

        RequestDispatcher rd = req.getRequestDispatcher("/productdescription.jsp");
        rd.forward(req, resp);
    }
}
