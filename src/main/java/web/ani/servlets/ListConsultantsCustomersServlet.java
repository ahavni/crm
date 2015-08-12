package web.ani.servlets;

import org.apache.log4j.Logger;
import web.ani.beans.Consultant;
import web.ani.beans.Customer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ListConsultantsCustomersServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(ListConsultantsCustomersServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Entering " + this.getClass().toString() + " servlet, doGet() method ");
        Consultant user = (Consultant)req.getSession().getAttribute("user");
        req.setAttribute("usersList", user.listConsultantsCustomers(user));
        getServletConfig().getServletContext().getRequestDispatcher("/listUsers.jsp").forward(req, resp);
    }
}
