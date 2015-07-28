package web.ani.servlets;

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
import java.util.List;

public class ListUsersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        Connection conn = null;

        try {
            conn = DBUtils.createDBConnection();
            ArrayList<User> userList = DBUtils.getUsersFromDB(conn);
            req.setAttribute("usersList", userList);
            /*
            if (userList.isEmpty()){
                System.out.println("Sorry No Users");
            }else {
                for (User user : userList) {
                    System.out.println(user.toString());
                }
            }*/
            getServletConfig().getServletContext().getRequestDispatcher("/listUsers.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                DBUtils.closeDBConnection(conn);
            }
        }
    }
}
