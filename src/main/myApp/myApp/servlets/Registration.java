package myApp.servlets;

import myApp.DAO.PersonsDAO;
import myApp.entity.PersonsEntity;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Registration extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    public void process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        //Creating new Person
        PersonsEntity person = new PersonsEntity(req.getParameter("name"), req.getParameter("surname"), req.getParameter("birthdate"), req.getParameter("email"), req.getParameter("password"), 1);
        new PersonsDAO().create(person);
        //Login
        req.getSession().setAttribute("userID", new PersonsDAO().getPersonByEmail(req.getParameter("email")).getId());

        RequestDispatcher rd = req.getRequestDispatcher("/start");
        rd.forward(req, resp);
    }
}
