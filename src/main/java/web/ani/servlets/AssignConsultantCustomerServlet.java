package web.ani.servlets;

import org.apache.log4j.Logger;
import web.ani.beans.User;
import web.ani.utils.DBUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class AssignConsultantCustomerServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(AssignConsultantCustomerServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Entering " + this.getClass().toString() + " servlet, doGet() method ");

        ArrayList<User> consultantsList;
        ArrayList<User> customersList;

        consultantsList = DBUtils.getUsersFromDB("consultant");
        customersList = DBUtils.getUsersFromDB("customer");

        req.setAttribute("consultant", consultantsList);
        req.setAttribute("customer", customersList);
        resp.sendRedirect("/assignCustomerConsultant.jsp");
        logger.info("Redirect user object to assignCustomerConsultant.jsp");

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Entering " + this.getClass().toString() + " servlet, doPost() method ");
        logger.info("Customer " + req.getParameter("selected_customer") + " assigned to consultant: " + req.getParameter("selected_consultant"));

        DBUtils.assignUsers(DBUtils.getUserID(req.getParameter("selected_consultant")),
                            DBUtils.getUserID(req.getParameter("selected_customer")));

        resp.sendRedirect("home.jsp");
    }
}
