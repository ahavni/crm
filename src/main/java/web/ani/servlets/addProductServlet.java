package web.ani.servlets;

import org.apache.log4j.Logger;
import web.ani.utils.DBUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class AddProductServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(AddProductServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Entering " + this.getClass().toString() + " servlet, doPost() method ");

        Connection conn = null;
        try {
            conn = DBUtils.createDBConnection();
            DBUtils.addProduct(conn, req.getParameter("product_name"));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                DBUtils.closeDBConnection(conn);
            }
        }

        req.getRequestDispatcher("addProduct.html").forward(req, resp);
        logger.info("Redirect user object to addProduct.jsp");
    }


}
