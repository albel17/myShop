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
 * Created by Admin on 27.11.15.
 */
public class SubmitUserChange extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = (Integer) req.getSession().getAttribute("userID");
        String password = req.getParameter("password");
        try {
            PersonsDAO personsDAO = new PersonsDAO();
            PersonsEntity user = personsDAO.getPersonByID(id);
            if (user.getPassword().equals(password)) {
                user.setName(req.getParameter("name"));
                user.setSurname(req.getParameter("surname"));
                user.setBirthdate(req.getParameter("birthdate"));
                user.setEmail(req.getParameter("email"));
                if(req.getParameter("newpassword")!="")
                user.setPassword(req.getParameter("newpassword"));
                personsDAO.update(user);
            }
    } catch (Exception e){}
        finally {
            RequestDispatcher rd = req.getRequestDispatcher("/profile");
            rd.forward(req, resp);
        }
    }
}
