package myApp.servlets;

import myApp.DAO.PersonsDAO;
import myApp.entity.AddressesEntity;
import myApp.entity.PersonsEntity;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class CheckoutContinue extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String deliverymethod = req.getParameter("deliverymethod");
        String paymentmethod = req.getParameter("paymentmethod");
        req.getSession().setAttribute("paymentmethod", paymentmethod);
        req.getSession().setAttribute("deliverymethod", deliverymethod);
        RequestDispatcher rd = req.getRequestDispatcher("/profile");
        if(deliverymethod.equals("delivery")){
            rd = req.getRequestDispatcher("/checkoutcontinue.jsp");
            int id = (Integer) req.getSession().getAttribute("userID");
            PersonsEntity person = new PersonsDAO().getPersonByID(id);
            Collection<AddressesEntity> addresslist = person.getAddressesById();
            req.setAttribute("addresslist",addresslist);
        }
        rd.forward(req,resp);
    }
}
