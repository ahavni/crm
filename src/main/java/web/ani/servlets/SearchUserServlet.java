package web.ani.servlets;

import org.apache.derby.iapi.util.StringUtil;
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
import org.apache.commons.lang3.StringUtils;

public class SearchUserServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(SearchUserServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Entering " + this.getClass().toString() + " servlet, doPost() method ");
        StringBuffer whereCause = new StringBuffer();
        String relation = "AND";

        if (req.getParameter("first_name") != "") {
            whereCause.append("firstName like '%" + req.getParameter("first_name") + "%' ");
        }
        if (req.getParameter("last_name") != "") {
            whereCause.append(relation + " lastName like '%" + req.getParameter("last_name") + "%' ");
        }
        if (req.getParameter("age") != "") {
            whereCause.append(relation + " age=" + Integer.parseInt(req.getParameter("age")) + " ");
        }
        if (req.getParameter("address") != "") {
            whereCause.append(relation + " address like '%" + req.getParameter("address") + "%' ");
        }
        if (req.getParameter("email") != "") {
            whereCause.append(relation + " email like '%" + req.getParameter("email") + "%' ");
        }
        if (req.getParameter("sex") != null) {
            whereCause.append(relation + " sex=" + req.getParameter("sex") + " ");
        }
        if (req.getParameter("customer") != null) {
            whereCause.append("user_type = customer ");
        }

        String[] searchedUserTypes = req.getParameterValues("user_type");
        if (searchedUserTypes != null && searchedUserTypes.length > 0) {
            String seed = StringUtils.join(searchedUserTypes, "','");
            whereCause.append("AND user_type in('" + seed + "')");
        }

        ArrayList<User> userList;
        userList = DBUtils.searchUsersFromDB(whereCause.toString());
        req.setAttribute("usersList", userList);
        getServletConfig().getServletContext().getRequestDispatcher("/listUsers.jsp").forward(req, resp);
        logger.info("Redirect user object to listUsers.jsp");
    }
}
