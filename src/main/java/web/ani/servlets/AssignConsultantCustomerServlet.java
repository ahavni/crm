package web.ani.servlets;

import org.apache.log4j.Logger;
import web.ani.beans.User;
import web.ani.utils.DBUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class AssignConsultantCustomerServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(AssignConsultantCustomerServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Entering " + this.getClass().toString() + " servlet, doGet() method ");

        ArrayList<User> consultantsList;
        ArrayList<User> customersList;
        Connection conn = null;
        try {
            conn = DBUtils.createDBConnection();
            consultantsList = DBUtils.getUsersFromDB(conn, "consultant");
            customersList = DBUtils.getUsersFromDB(conn, "customer");

            req.setAttribute("consultant", consultantsList);
            req.setAttribute("customer", customersList);
            getServletConfig().getServletContext().getRequestDispatcher("/assignCustomerConsultant.jsp").forward(req, resp);
            logger.info("Redirect user object to assignCustomerConsultant.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                DBUtils.closeDBConnection(conn);
            }
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Entering " + this.getClass().toString() + " servlet, doPost() method ");
        logger.info("Customer " + req.getParameter("selected_customer") + " assigned to consultant: " + req.getParameter("selected_consultant"));

        Connection conn = null;
        try {
            conn = DBUtils.createDBConnection();
            DBUtils.assignUsers(conn,
                                DBUtils.getUserID(conn, req.getParameter("selected_consultant")),
                                DBUtils.getUserID(conn, req.getParameter("selected_customer")));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                DBUtils.closeDBConnection(conn);
            }
        }
//        req.setAttribute("user", user);
//        req.getRequestDispatcher("home.jsp").forward(req, resp);
        //???
    }
}
