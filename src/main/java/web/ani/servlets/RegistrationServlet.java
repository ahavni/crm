package web.ani.servlets;

import web.ani.beans.User;
import web.ani.utils.DBUtils;

import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

/**
 * Created by Anichka on 10.4.2015 г..
 */
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User newUser = new User(req.getParameter("first_name"),
                                req.getParameter("last_name"),
                                Integer.parseInt(req.getParameter("age")),
                                req.getParameter("address"),
                                req.getParameter("email"),
                                req.getParameter("sex")
                                );
        Connection conn = null;

        try {
            conn = DBUtils.createDBConnection();
            DBUtils.addUser(conn, newUser);
            /*
            PrintWriter printWriter = resp.getWriter();
            printWriter.println("Congrate you have created a User " +
                    newUser.getFistName() + ", lastname: "
                    + newUser.getLastName() + ", age: " +
                    newUser.getAge());
            */
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                DBUtils.closeDBConnection(conn);
            }

            req.setAttribute("created_user", newUser);
            req.getRequestDispatcher("success.jsp").forward(req, resp);
        }
    }
}

