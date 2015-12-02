package myApp.servlets;

import myApp.DAO.AddressDAO;
import myApp.DAO.PersonsDAO;
import myApp.entity.AddressesEntity;
import myApp.entity.PersonsEntity;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddAddress extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String country = req.getParameter("country");
        String city = req.getParameter("city");
        String postalcode = req.getParameter("postalcode");
        String street = req.getParameter("street");
        String house = req.getParameter("house");
        String flat = req.getParameter("flat");
        PersonsEntity person = new PersonsDAO().getPersonByID((Integer)req.getSession().getAttribute("userID")); //get person by userID
        AddressesEntity address = new AddressesEntity(country,city,postalcode,street,house,flat,person); //creating new address
        new AddressDAO().create(address);

        RequestDispatcher rd = req.getRequestDispatcher("/addresslist");
        rd.forward(req,resp);
    }
}
