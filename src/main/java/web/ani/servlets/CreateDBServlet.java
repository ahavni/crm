package web.ani.servlets;

import org.apache.log4j.Logger;
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
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class CreateDBServlet extends HttpServlet {
    private Connection conn;
    final static Logger logger = Logger.getLogger(CreateDBServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Entered " + this.getClass().toString());
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        try {
            this.conn = DBUtils.createDBConnection();
            DatabaseMetaData dbm = conn.getMetaData();

            DBUtils.createUserRelationsTable(this.conn);

        } catch (SQLException e) {
            logger.info("Problem initializing the sql connection ");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            logger.info("Some class has not been found");
            e.printStackTrace();
        }
    }
}