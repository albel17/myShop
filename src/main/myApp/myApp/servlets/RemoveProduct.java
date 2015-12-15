package myApp.servlets;

import myApp.services.ProductManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RemoveProduct extends HttpServlet {
    private ProductManager productManager = new ProductManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int categoryId = productManager.getCategoryId(Integer.parseInt(req.getParameter("id")));
        productManager.delete(req.getParameter("id"));
        RequestDispatcher rd = req.getRequestDispatcher("/admin/editproducts?id="+categoryId);
        rd.forward(req, resp);
    }
}
