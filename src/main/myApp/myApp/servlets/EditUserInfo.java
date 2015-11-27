package myApp.servlets;

import myApp.DAO.PersonsDAO;
import myApp.entity.PersonsEntity;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditUserInfo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id =(Integer) req.getSession().getAttribute("userID");
        PersonsEntity person = new PersonsDAO().getPersonByID(id);
        req.setAttribute("person",person);
        RequestDispatcher rd = req.getRequestDispatcher("/edituserinfo.jsp");
        rd.forward(req,resp);
    }
}
