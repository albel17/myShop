package myApp.filters;

import javax.servlet.*;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class ProfileFilter extends HttpFilter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        Object userId =  ((HttpServletRequest)req).getSession().getAttribute("userID");
        if(userId == null)
        {RequestDispatcher rd = req.getRequestDispatcher("/start");
            rd.forward(req, res);
        }
        else
            chain.doFilter(req,res);
    }
}
