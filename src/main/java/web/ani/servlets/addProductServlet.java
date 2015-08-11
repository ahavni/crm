package web.ani.servlets;

import org.apache.log4j.Logger;
import web.ani.beans.Consultant;
import web.ani.utils.DBUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddProductServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(AddProductServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Entering " + this.getClass().toString() + " servlet, doPost() method ");
        Consultant user = (Consultant)req.getSession().getAttribute("user");
        user.addProduct(req.getParameter("product_name"));
        resp.sendRedirect("/addProduct.html");
        logger.info("Redirect user object to addProduct.jsp");
    }
}
