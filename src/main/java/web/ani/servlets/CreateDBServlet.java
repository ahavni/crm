package web.ani.servlets;

import org.apache.log4j.Logger;
import web.ani.utils.DBUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateDBServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(CreateDBServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Entering " + this.getClass().toString() + " servlet, doGet() method ");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        logger.info("Entering " + this.getClass().toString() + " servlet, init() method ");

        DBUtils.createProductsRelationsTable();
    }
}