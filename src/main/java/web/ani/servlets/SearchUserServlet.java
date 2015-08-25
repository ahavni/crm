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
import org.apache.commons.lang3.StringUtils;

public class SearchUserServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(SearchUserServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Entering " + this.getClass().toString() + " servlet, doPost() method ");
        StringBuffer whereClause = new StringBuffer();
        String relation = "AND";

        if (req.getParameter("first_name") != "") {
            whereClause.append("firstName like '%" + req.getParameter("first_name") + "%' " + relation);
        }
        if (req.getParameter("last_name") != "") {
            whereClause.append(" lastName like '%" + req.getParameter("last_name") + "%' " + relation);
        }
        if (req.getParameter("age") != "") {
            whereClause.append(" age=" + Integer.parseInt(req.getParameter("age")) + " " + relation);
        }
        if (req.getParameter("address") != "") {
            whereClause.append(" address like '%" + req.getParameter("address") + "%' " + relation);
        }
        if (req.getParameter("email") != "") {
            whereClause.append(" email like '%" + req.getParameter("email") + "%' " + relation);
        }
        if (req.getParameter("sex") != null) {
            whereClause.append(" sex='" + req.getParameter("sex") + "' " + relation);
        }

        String[] searchedUserTypes = req.getParameterValues("user_type");
        if (searchedUserTypes != null && searchedUserTypes.length > 0) {
            String seed = StringUtils.join(searchedUserTypes, "','");
            whereClause.append(" user_type in('" + seed + "')");
        }

        logger.info("WHERE cause before has AND check is: " + whereClause);
        boolean hasAND = whereClause.substring(whereClause.length()-3).equals("AND");
        ArrayList<User> userList;
        if(hasAND){
            userList = DBUtils.searchUsersInDB(whereClause.substring(0, whereClause.length()-3).toString());
        }else{
            userList = DBUtils.searchUsersInDB(whereClause.toString());
        }

        req.setAttribute("usersList", userList);
        getServletConfig().getServletContext().getRequestDispatcher("/listUsers.jsp").forward(req, resp);
        logger.info("Redirect user object to listUsers.jsp");
    }
}
