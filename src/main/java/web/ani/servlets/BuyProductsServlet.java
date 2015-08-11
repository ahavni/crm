package web.ani.servlets;

import org.apache.log4j.Logger;
import web.ani.beans.Customer;
import web.ani.beans.User;
import web.ani.utils.DBUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class BuyProductsServlet extends HttpServlet{
    final static Logger logger = Logger.getLogger(BuyProductsServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Entering " + this.getClass().toString() + " servlet, doGet() method ");

        ArrayList<String> productsList;
        productsList = DBUtils.getProductsFromDB();
        req.setAttribute("products", productsList);
        getServletConfig().getServletContext().getRequestDispatcher("/buyProduct.jsp").forward(req, resp);
        logger.info("Redirect user object to buyProduct.jsp");

    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Entering " + this.getClass().toString() + " servlet, doPost() method ");
        logger.info("Customer " + req.getParameter("selected_customer") + " bought product: " + req.getParameter("selected_product"));

        Customer user = (Customer) req.getSession().getAttribute("user");
        user.buyProducts(user.getEmail(),req.getParameter("selected_product"));
        resp.sendRedirect("home.jsp");
    }
}
