
package com.educacionit.delivery.servlet;


import com.educacionit.delivery.beans.User;
import com.educacionit.delivery.dao.DBConnectionManager;
import com.educacionit.delivery.services.ISecurity;
import com.educacionit.delivery.services.SecurityException;
import com.educacionit.delivery.services.support.SecuritySupport;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;



@WebServlet ("/login")
public class LoginServlet extends HttpServlet {


    private ISecurity security;

    private static final Logger logger = Logger.getLogger (LoginServlet.class);


    @Override
    public void init () throws ServletException {

        logger.debug ("Getting Servlet Context...");
        ServletContext ctx = this.getServletContext ();

        logger.debug ("Initializing Security Support Object...");
        this.security = new SecuritySupport ((DBConnectionManager) ctx.getAttribute ("db"));
    }

    @Override
    protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {

            logger.debug (String.format ("Executing loggin user %s from %s", req.getParameter ("username"), req.getRemoteHost ()));
            User u = security.login (req.getParameter ("username"), req.getParameter ("pw"));

            logger.debug (String.format ("User %s from %s was logged !!!", req.getParameter ("username"), req.getRemoteHost ()));
            HttpSession session = req.getSession ();
            session.setAttribute ("user", u);

            resp.sendRedirect ("home.jsp");

        } catch (SecurityException se) {

            logger.error (se.getMessage ());
            resp.sendRedirect ("index.jsp?err=001");
        } catch (Exception e) {
            logger.error(e.getMessage());
            resp.sendRedirect ("index.jsp?err=000");
        }
    }
}