package web.ani.servlets;

import web.ani.utils.DBUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class CreateDBServlet extends HttpServlet {
    private Connection conn;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Properties prop = new Properties();
        PrintWriter out = new PrintWriter(System.out);
        try {
            prop = conn.getClientInfo();
            out = resp.getWriter();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                DBUtils.closeDBConnection(conn);
            }
        }
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        try {
            this.conn = DBUtils.createDBConnection();
            DatabaseMetaData dbm = conn.getMetaData();
            // check if table is there
            ResultSet tables = dbm.getTables(null, null, "USERS", null);
            if (tables.next()) {
                // Table exists
                System.out.println("Table USERS - already existing");
            } else {
                DBUtils.createUserTable(this.conn);
            }
        } catch (SQLException e) {
            System.out.println("Problem initializing the sql connection ");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Some class has not been found");
            e.printStackTrace();
        }
    }
}