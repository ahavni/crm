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
import java.util.List;

public class ListUsersServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(ListUsersServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        logger.info("Entering " + this.getClass().toString() + " servlet, doGet() method ");

        Connection conn = null;
        ArrayList<User> userList;
        try {
            conn = DBUtils.createDBConnection();
            userList = DBUtils.getUsersFromDB(conn);

            req.setAttribute("usersList", userList);
            getServletConfig().getServletContext().getRequestDispatcher("/listUsers.jsp").forward(req, resp);
            logger.info("Redirect user object to listUsers.jsp");
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
}
