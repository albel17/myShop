package myApp.servlets;

import myApp.DAO.CategoriesDAO;
import myApp.DAO.ParametersDAO;
import myApp.DAO.ProductsDAO;
import myApp.entity.AttributesEntity;
import myApp.entity.CategoriesEntity;
import myApp.entity.ParametersEntity;
import myApp.entity.ProductsEntity;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class AddProduct extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String currentprice = req.getParameter("currentprice");
        String size = req.getParameter("size");
        String weight = req.getParameter("weight");
        String description = req.getParameter("description");

        CategoriesEntity category = new CategoriesDAO().getCategoryByID(Integer.parseInt(req.getParameter("categoryId")));
        Collection<AttributesEntity> attributes = category.getAttributes();
        Map<AttributesEntity,String> attributeNames = new HashMap<AttributesEntity,String>();
        for(AttributesEntity attribute : attributes){
            attributeNames.put(attribute, req.getParameter(String.valueOf(attribute.getId())));
        }

        ProductsEntity newProduct = new ProductsEntity(name,currentprice,size,weight,description,category.getId());
        newProduct = new ProductsDAO().create(newProduct);

        for(Map.Entry<AttributesEntity,String> entry : attributeNames.entrySet()){
            new ParametersDAO().create(new ParametersEntity(entry.getValue(), newProduct, entry.getKey()));
        }

        RequestDispatcher rd = req.getRequestDispatcher("/editproducts?id="+category.getId());
        rd.forward(req,resp);
    }
}
