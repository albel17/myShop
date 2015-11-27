package myApp.servlets;

import myApp.DAO.AddressDAO;
import myApp.entity.AddressesEntity;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteAddress extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        new AddressDAO().delete(id);
        RequestDispatcher rd = req.getRequestDispatcher("/addresslist");
        rd.forward(req,resp);
    }
}
