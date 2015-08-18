package web.ani.servlets;

import org.apache.log4j.Logger;
import web.ani.beans.Consultant;
import web.ani.beans.User;
import web.ani.utils.DBUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class AssignUsersServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(AssignUsersServlet.class);
    Consultant user;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Entering " + this.getClass().toString() + " servlet, doGet() method ");
        user = (Consultant)req.getSession().getAttribute("user");
        req.setAttribute("consultant", user.listAllConsultants());
        req.setAttribute("customer", user.listAllCustomers());
        getServletConfig().getServletContext().getRequestDispatcher("/assignUsersInDB.jsp").forward(req, resp);
        logger.info("Redirect user object to assignUsersInDB.jsp");
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Entering " + this.getClass().toString() + " servlet, doPost() method ");
        logger.info("Customer " + req.getParameter("selected_customer") + " assigned to consultant: " + req.getParameter("selected_consultant"));
        user.assignUsers(req.getParameter("selected_consultant"),req.getParameter("selected_customer"));
        resp.sendRedirect("home.jsp");
    }
}
