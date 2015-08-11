package web.ani.servlets;

import org.apache.log4j.Logger;
import web.ani.beans.Consultant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ListCustomersServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(ListCustomersServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Entering " + this.getClass().toString() + " servlet, doGet() method ");
        Consultant user = (Consultant)req.getSession().getAttribute("user");
        req.setAttribute("usersList", user.listAllCustomers());
        getServletConfig().getServletContext().getRequestDispatcher("/listUsers.jsp").forward(req, resp);
        logger.info("Redirect user object to listUsers.jsp");
    }
}
