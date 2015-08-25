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

public class LoginServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(LoginServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Entering " + this.getClass().toString() + " servlet, doPost() method ");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        logger.info(" username = " + username + " and password = " + password);
        User user = null;
        user = DBUtils.getUserByEmailFromDB(username);
        String encryptPass = MD5.crypt(password);
        logger.info("Encrypted password is " + encryptPass);
        boolean authentication = false;

        if (user != null) {
            logger.info("User exists: " + user);
            logger.info("Crypt password is " + user.getPassword());
            logger.info("Result from comparison the two pass is " + user.getPassword().equals(encryptPass));
            logger.info("User authentication is successful");
            if (user.getPassword().equals(encryptPass)) {
                authentication = true;
            } else {
                logger.info("User authentication did not pass: DB user = " + user.toString());
            }
        }

        if (authentication) {
            logger.info("Create user's session and load user's data in it");
            req.getSession().setAttribute("user", user);
            resp.sendRedirect("home.jsp");
            logger.info("Redirect user object to home.jsp");
        } else {
            logger.info("Redirect to login.jsp");
            req.setAttribute("login_result", "false");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}
