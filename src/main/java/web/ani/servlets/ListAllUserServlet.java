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
import java.util.List;

/**
 * Created by Anichka on 27.5.2015 г..
 */
public class ListAllUserServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        Connection conn = null;
        try {
            conn = DBUtils.createDBConnection();
            List<User> userList = DBUtils.getUsersFromDB(conn);

            req.setAttribute("users", userList);
            req.getRequestDispatcher("listAllUsersForm.jsp").forward(req, resp);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(conn != null){
                DBUtils.closeDBConnection(conn);
            }
        }
    }

}