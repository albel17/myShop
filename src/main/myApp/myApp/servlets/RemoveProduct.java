package myApp.servlets;

import myApp.DAO.ParametersDAO;
import myApp.DAO.ProductsDAO;
import myApp.entity.ParametersEntity;
import myApp.entity.ProductsEntity;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class RemoveProduct extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        ProductsEntity product = new ProductsDAO().getProductByID(id);
        int categoryId = product.getCategoryId();
        Collection<ParametersEntity> parameters = product.getParametersesById();
        ParametersDAO parametersDAO = new ParametersDAO();
        for(ParametersEntity parameter : parameters){
            parametersDAO.delete(parameter.getId());
        }
        new ProductsDAO().delete(id);
        RequestDispatcher rd = req.getRequestDispatcher("/admin/editproducts?id="+categoryId);
        rd.forward(req, resp);
    }
}
