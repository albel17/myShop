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
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String birthdate = req.getParameter("birthdate");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        PersonsEntity person = new PersonsEntity(name,surname,birthdate,email,password,1);
        new PersonsDAO().create(person);
        req.getSession().setAttribute("userID",new PersonsDAO().getPersonByEmail(email).getId());


        RequestDispatcher rd = req.getRequestDispatcher("/start");
        rd.forward(req,resp);
    }
}
