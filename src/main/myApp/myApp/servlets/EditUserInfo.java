package myApp.servlets;

import myApp.services.PersonManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditUserInfo extends HttpServlet {
    private PersonManager personManager = new PersonManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("person", personManager.find(req.getSession().getAttribute("userID")));
        RequestDispatcher rd = req.getRequestDispatcher("/edituserinfo.jsp");
        rd.forward(req, resp);
    }
}
