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

public class UserDetailsServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

        if (req.getParameter("save_changes") != null) {
            updateUser(user);
        }
        req.setAttribute("user", user);// TO BE RENAMED
        req.getRequestDispatcher("success.jsp").forward(req, resp);
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
}
