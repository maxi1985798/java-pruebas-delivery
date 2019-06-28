
package com.educacionit.delivery.servlet;


import com.educacionit.delivery.dao.DBConnectionManager;
import com.educacionit.delivery.services.ISecurity;
import com.educacionit.delivery.services.support.SecuritySupport;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.ServletContext;
import org.apache.log4j.Logger;


public class UserConfirmedServlet extends HttpServlet {

    private ISecurity security;
    private static final Logger logger = Logger.getLogger (UserConfirmedServlet.class);
    
    @Override
    public void init () throws ServletException {

        logger.debug ("Getting Servlet Context...");
        ServletContext ctx = this.getServletContext ();

        logger.debug ("Initializing Security Support Object...");
        this.security = new SecuritySupport ((DBConnectionManager) ctx.getAttribute ("db"));
    }
    
    @Override
    protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mailToConfirm = req.getParameter("mail");
        logger.debug(String.format("Confirming user %s", mailToConfirm));
        security.confirmUser(mailToConfirm);
        logger.info(String.format("%s confirmed", mailToConfirm));
        resp.sendRedirect ("index.jsp");
    }
}