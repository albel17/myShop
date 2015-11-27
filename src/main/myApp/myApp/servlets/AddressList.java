package myApp.servlets;

import myApp.DAO.PersonsDAO;
import myApp.entity.PersonsEntity;
import myApp.entity.AddressesEntity;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class AddressList extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = (Integer) req.getSession().getAttribute("userID");
        PersonsEntity person = new PersonsDAO().getPersonByID(id);
        Collection<AddressesEntity> addresslist = person.getAddressesById();
        req.setAttribute("addresslist",addresslist);
        RequestDispatcher rd = req.getRequestDispatcher("/addresslist.jsp");
        rd.forward(req, resp);
    }
}
