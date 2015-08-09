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

public class LoginServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(LoginServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Entering " + this.getClass().toString() + " servlet, doPost() method ");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        logger.info(" username = " + username + " and password = " + password);

        Connection conn = null;
        User user = null;
        try {
            conn = DBUtils.createDBConnection();
            user = DBUtils.getUserByEmail(conn, username);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                DBUtils.closeDBConnection(conn);
            }
        }

        String encryptPass = MD5.crypt(password);
        logger.info("Crypt password is " + user.getPassword());
        logger.info("Encrypted password is " + encryptPass);

        if(user.getPassword().equals(encryptPass)){
            logger.info("Result from comparison the two pass is" + user.getPassword().equals(encryptPass));
            logger.info("User authentication is successful");
            req.setAttribute("user", user);
            req.getRequestDispatcher("home.jsp").forward(req, resp);
            logger.info("Redirect user object to home.jsp");
        }else{
            logger.info("User authentication did not pass: DB user = " + user.toString());
        }
    }
}
