package myApp.servlets;

import myApp.bin.Bin;
import myApp.bin.BinItem;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Admin on 26.11.15.
 */
public class AddToBin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Bin bin = (Bin) req.getSession().getAttribute("bin");
        if (bin==null) {
            bin = new Bin();
            bin.add(new BinItem(id,1));
        }
        else
            bin.add(new BinItem(id, 1));
        req.getSession().setAttribute("bin", bin);
        RequestDispatcher rd = req.getRequestDispatcher("/productDescription?id="+id);
        rd.forward(req,resp);
    }
}
