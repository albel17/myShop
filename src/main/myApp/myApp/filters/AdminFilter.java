package myApp.filters;

import myApp.DAO.PersonsDAO;
import myApp.entity.PersonsEntity;

import javax.servlet.*;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AdminFilter extends HttpFilter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        //testing if user can get access to admin folder
        Integer id = (Integer) ((HttpServletRequest) req).getSession().getAttribute("userID");
        RequestDispatcher rd = req.getRequestDispatcher("/start");
        if (id != null) { // user is not logged in
            PersonsEntity person = new PersonsDAO().getPersonByID(id);
            if (person.getPersonType() == 2) {
                chain.doFilter(req, res); //admin logged in
            } else {
                rd.forward(req, res); //not admin
            }
        } else {
            rd.forward(req, res); //anon
        }
    }
}
