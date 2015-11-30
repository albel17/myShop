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

public class SubmitProductChange extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String currentprice = req.getParameter("currentprice");
        String size = req.getParameter("size");
        String weight = req.getParameter("weight");
        String description = req.getParameter("description");

        int id = Integer.parseInt(req.getParameter("productId"));
        ProductsEntity product = new ProductsDAO().getProductByID(id);
        product.setName(name);
        product.setCurrentPrice(currentprice);
        product.setSize(size);
        product.setWeight(weight);
        product.setDescription(description);

        CategoriesEntity category = new CategoriesDAO().getCategoryByID(new ProductsDAO().getProductByID(Integer.parseInt(req.getParameter("productId"))).getCategoryId());
        Collection<AttributesEntity> attributes = category.getAttributes();
        Map<AttributesEntity, String> attributeValues = new HashMap<AttributesEntity, String>();
        for(AttributesEntity attribute : attributes){
            attributeValues.put(attribute, req.getParameter(String.valueOf(attribute.getId())));
        }

        product = new ProductsDAO().update(product);

        ParametersDAO parametersDAO = new ParametersDAO();
        for(Map.Entry<AttributesEntity,String> entry : attributeValues.entrySet()){
            ParametersEntity parameter = parametersDAO.getParameterByAttributeIdProductId(entry.getKey(), product);
            parameter.setValue(entry.getValue());
            parametersDAO.update(parameter);
        }

        RequestDispatcher rd = req.getRequestDispatcher("/editproduct?id="+product.getId());
        rd.forward(req,resp);
    }
}
