
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


@WebServlet ("/register")
public class RegisterServlet extends HttpServlet {


    private ISecurity security;

    private static final Logger logger = Logger.getLogger (RegisterServlet.class);


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

            User u = new User ();

            u.setUserName (req.getParameter ("userName"));
            u.setName (req.getParameter ("name"));
            u.setLastName (req.getParameter ("lastName"));
            u.setEmail (req.getParameter ("email"));
            u.setMobile (req.getParameter ("mobile"));
            u.setAddress (req.getParameter ("address"));
            u.setPassword (req.getParameter ("pw"));


            if (u.getAddress().equals("") ||
                u.getEmail ().equals ("") ||
                u.getLastName ().equals ("") ||
                u.getMobile ().equals ("") ||
                u.getName ().equals ("") ||
                u.getPassword ().equals ("") ||
                u.getUserName ().equals ("")) {

                resp.sendRedirect ("register.jsp?err=002");
            }

            if (!u.getPassword ().equals (req.getParameter ("pw2"))) {

                resp.sendRedirect ("register.jsp?err=003");
            }
            security.signUp(u);
            
            HttpSession session = req.getSession ();
            session.setAttribute ("user", u);

            resp.sendRedirect ("home.jsp");

        } catch (SecurityException se) {

            resp.sendRedirect ("register.jsp?err=001");
        }
    }
}