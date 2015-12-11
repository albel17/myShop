package myApp.servlets;

import myApp.services.AddressManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CheckoutContinue extends HttpServlet {
    private AddressManager addressManager = new AddressManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String deliverymethod = req.getParameter("deliverymethod");
        String paymentmethod = req.getParameter("paymentmethod");
        req.getSession().setAttribute("paymentmethod", paymentmethod);
        req.getSession().setAttribute("deliverymethod", deliverymethod);
        RequestDispatcher rd = req.getRequestDispatcher("/profile");
        if(deliverymethod.equals("delivery")){
            rd = req.getRequestDispatcher("/checkoutcontinue.jsp");
            req.setAttribute("addresslist",
                    addressManager.getAddressListByUserId((Integer) req.getSession().getAttribute("userID")));
        }
        rd.forward(req,resp);
    }
}
