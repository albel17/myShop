package myApp.servlets;

import myApp.DAO.PersonsDAO;
import myApp.entity.PersonsEntity;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Admin on 26.11.15.
 */
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        try {
            PersonsEntity user = new PersonsDAO().getPersonByEmail(login);
            if (user.getPassword().equals(password))
                req.getSession().setAttribute("userID", user.getId());

        }
        catch (Exception e){}
        finally {
            RequestDispatcher rd = req.getRequestDispatcher("/start");
            rd.forward(req, resp);
        }
    }
}
