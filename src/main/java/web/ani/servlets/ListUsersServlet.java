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

public class ListUsersServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(ListUsersServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Entering " + this.getClass().toString() + " servlet, doGet() method ");

        ArrayList<User> userList;
        userList = DBUtils.getUsersFromDB();
        req.setAttribute("usersList", userList);
        getServletConfig().getServletContext().getRequestDispatcher("/listUsers.jsp").forward(req, resp);//TODO
    }
}
