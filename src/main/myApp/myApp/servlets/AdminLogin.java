package myApp.servlets;

import myApp.DAO.PersonsDAO;
import myApp.entity.PersonsEntity;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminLogin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = (Integer) req.getSession().getAttribute("userID");
        PersonsEntity person = new PersonsDAO().getPersonByID(id);
        RequestDispatcher rd = req.getRequestDispatcher("/start");
        if(person.getPersonType()==2){
            rd = req.getRequestDispatcher("/admin.jsp");
        }
        rd.forward(req,resp);
    }
}
