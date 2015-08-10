package web.ani.servlets;

import org.apache.log4j.Logger;
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

public class BuyProductsServlet extends HttpServlet{
    final static Logger logger = Logger.getLogger(BuyProductsServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Entering " + this.getClass().toString() + " servlet, doGet() method ");

        ArrayList<String> productsList;
        Connection conn = null;
        try {
            conn = DBUtils.createDBConnection();
            productsList = DBUtils.getProductsFromDB(conn);

            req.setAttribute("products", productsList);
            getServletConfig().getServletContext().getRequestDispatcher("/buyProduct.jsp").forward(req, resp);
            logger.info("Redirect user object to buyProduct.jsp");
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Entering " + this.getClass().toString() + " servlet, doPost() method ");
        logger.info("Customer " + req.getParameter("selected_customer") + " bought product: " + req.getParameter("selected_product"));

        Connection conn = null;
        User user = (User) req.getSession().getAttribute("user");
        try {
            conn = DBUtils.createDBConnection();
            DBUtils.assignProductToUser(conn,
                                        DBUtils.getUserID(conn, user.getEmail()),
                                        DBUtils.getProductID(conn, req.getParameter("selected_product")));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                DBUtils.closeDBConnection(conn);
            }
        }
        resp.sendRedirect("home.jsp");
    }
}
