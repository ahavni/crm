package web.ani.servlets;

import org.apache.log4j.Logger;
import web.ani.beans.User;
import web.ani.utils.DBUtils;
import web.ani.utils.MD5;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class UserDetailsServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(UserDetailsServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Entering " + this.getClass().toString() + " servlet, doPost() method ");

        User user = new User(req.getParameter("first_name"),
                    req.getParameter("last_name"),
                    Integer.parseInt(req.getParameter("age")),
                    req.getParameter("address"),
                    req.getParameter("email"),
                    req.getParameter("sex"),
                    req.getParameter("user_type")
        );

        if(req.getParameter("save_changes") != null){
            if(req.getParameter("password") != "") {
                logger.info("Password is going to change");
                String hashPass = MD5.crypt(req.getParameter("password"));
                user.setPassword(hashPass);
                updateUser(user);
            }
            else{
                logger.info("Password has not been changed");
                updateUserExceptPass(user);
            }
        }

        logger.info("Update user bean in the session");
        req.setAttribute("user", user);
        req.getRequestDispatcher("home.jsp").forward(req, resp);
        logger.info("Redirect user object to home.jsp");
    }


    void updateUser(User newUserP) {
        Connection conn = null;
        try {
            conn = DBUtils.createDBConnection();
            DBUtils.updateUser(conn, newUserP);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                DBUtils.closeDBConnection(conn);
            }
        }
    }

    void updateUserExceptPass(User newUserP) {
        Connection conn = null;
        try {
            conn = DBUtils.createDBConnection();
            DBUtils.updateUserExceptPass(conn, newUserP);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                DBUtils.closeDBConnection(conn);
            }
        }
    }
}
