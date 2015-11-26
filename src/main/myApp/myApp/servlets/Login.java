package myApp.servlets;

import myApp.DAO.PersonsDAO;
import myApp.entity.PersonsEntity;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        RequestDispatcher rd = req.getRequestDispatcher("/start");
        try {
            PersonsEntity user = new PersonsDAO().getPersonByEmail(login);
            if (user.getPassword().equals(password))
                req.getSession().setAttribute("userID", user.getId());
            else throw new  Exception();

        }
        catch (Exception e){
            rd = req.getRequestDispatcher("/registration.jsp");
            req.setAttribute("error","Illegal Username or Password.");
        }
        finally {
            rd.forward(req, resp);
        }
    }
}
