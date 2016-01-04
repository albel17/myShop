package myApp.servlets;

import myApp.services.PersonManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Registration extends HttpServlet {
    private PersonManager personManager = new PersonManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    public void process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
//        req.getSession().setAttribute("userID", personManager.createWithParams(req.getParameter("name"),
//                req.getParameter("surname"), req.getParameter("birthdate"), req.getParameter("email"),
//                req.getParameter("password")));
        RequestDispatcher rd = req.getRequestDispatcher("/start");
        rd.forward(req, resp);
    }
}
