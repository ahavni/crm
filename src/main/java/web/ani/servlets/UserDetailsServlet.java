package web.ani.servlets;

import org.apache.log4j.Logger;
import web.ani.beans.User;
import web.ani.utils.MD5;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserDetailsServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(UserDetailsServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Entering " + this.getClass().toString() + " servlet, doPost() method ");
        User user = (User) req.getSession().getAttribute("user");
        logger.info("Getting user from session " + user.toString());

        logger.info("Getting updated user's data from jsp");
        user.setFistName(req.getParameter("first_name"));
        user.setLastName(req.getParameter("last_name"));
        user.setAge(Integer.parseInt(req.getParameter("age")));
        user.setAddress(req.getParameter("address"));
        user.setEmail(req.getParameter("email"));
        user.setSex(req.getParameter("sex"));
        user.setUserType(req.getParameter("user_type"));

        if (req.getParameter("save_changes").equals("Save")) {
            if (req.getParameter("password").isEmpty()) {
                logger.info("Password has not been changed");
                user.updateUserExceptPass(user);
            } else {
                logger.info("Password is going to change");
                String hashPass = MD5.crypt(req.getParameter("password"));
                user.setPassword(hashPass);
                user.updateUser(user);
            }
        }

        logger.info("Update user's details in the session");
        req.getSession().setAttribute("user", user);
        resp.sendRedirect("home.jsp");
        logger.info("Redirect user object to home.jsp");
    }
}
