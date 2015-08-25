package web.ani.servlets;

import org.apache.log4j.Logger;
import web.ani.beans.Consultant;
import web.ani.beans.Customer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class ListConsultantsCustomersServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(ListConsultantsCustomersServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Entering " + this.getClass().toString() + " servlet, doGet() method ");
        Consultant user = (Consultant)req.getSession().getAttribute("user");
        user.setCustomersList(user);
        req.setAttribute("usersList", user.getCustomersList(user));
        req.getRequestDispatcher("/listUsers.jsp").forward(req, resp);
    }
}
