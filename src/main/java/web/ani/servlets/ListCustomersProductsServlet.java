package web.ani.servlets;

import org.apache.log4j.Logger;
import web.ani.beans.Customer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ListCustomersProductsServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(ListCustomersProductsServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Entering " + this.getClass().toString() + " servlet, doGet() method ");
        Customer user = (Customer)req.getSession().getAttribute("user");
        user.setCustomersProductsList(user);
        req.setAttribute("productList", user.getCustomersProductsList(user));
        req.getRequestDispatcher("/listProducts.jsp").forward(req, resp);
    }
}
