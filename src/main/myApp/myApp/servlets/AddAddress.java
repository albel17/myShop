package myApp.servlets;

import myApp.services.AddressManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddAddress extends HttpServlet {
    private AddressManager addressManager = new AddressManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        addressManager.createWithParams(req.getParameter("country"), req.getParameter("city"),
                req.getParameter("postalcode"), req.getParameter("street"), req.getParameter("house"),
                req.getParameter("flat"), (Integer) req.getSession().getAttribute("userID"));

        RequestDispatcher rd = req.getRequestDispatcher("/addresslist");
        rd.forward(req, resp);
    }
}
