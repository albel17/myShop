package myApp.servlets;

import myApp.services.AddressManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddressList extends HttpServlet {
    AddressManager addressManager = new AddressManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("addresslist", addressManager.getAddressListByUserId((Integer) req.getSession().getAttribute("userID")));
        RequestDispatcher rd = req.getRequestDispatcher("/addresslist.jsp");
        rd.forward(req, resp);
    }
}
