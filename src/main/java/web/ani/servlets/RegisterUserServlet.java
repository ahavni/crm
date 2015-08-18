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

public class RegisterUserServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(RegisterUserServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Entering " + this.getClass().toString() + " servlet, doPost() method ");

        String hashPass = MD5.crypt(req.getParameter("password"));
        User user = new User(req.getParameter("first_name"),
                                req.getParameter("last_name"),
                                Integer.parseInt(req.getParameter("age")),
                                req.getParameter("address"),
                                req.getParameter("email"),
                                req.getParameter("sex"),
                                req.getParameter("user_type"),
                                hashPass
                                );

        DBUtils.addUserInDB(user);
        logger.info("Create user's session");
        req.getSession().setAttribute("user", user);

        resp.sendRedirect("home.jsp");
        logger.info("Redirect user object to home.jsp");
    }
}

