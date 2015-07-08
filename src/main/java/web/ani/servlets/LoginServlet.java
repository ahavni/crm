package web.ani.servlets;

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

/**
 * Created by Anichka on 13.6.2015 г..
 */
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
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

        if(user.getPassword().equals(MD5.crypt(password))){
            System.out.println("Match");
            req.setAttribute("found_user", user);
            req.getRequestDispatcher("home.jsp").forward(req, resp);
        }
    }
}
