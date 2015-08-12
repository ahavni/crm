package web.ani.servlets;

import org.apache.log4j.Logger;
import web.ani.beans.Consultant;
import web.ani.beans.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ListProductsServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(ListProductsServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Entering " + this.getClass().toString() + " servlet, doGet() method ");
        User user = (User)req.getSession().getAttribute("user");
        req.setAttribute("productList", user.listAllProducts());
        getServletConfig().getServletContext().getRequestDispatcher("/listProducts.jsp").forward(req, resp);
    }
}
