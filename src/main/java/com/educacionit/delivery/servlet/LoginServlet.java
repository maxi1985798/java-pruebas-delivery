
package com.educacionit.delivery.servlet;


import com.educacionit.delivery.beans.Restaurant;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Date;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.apache.log4j.Logger;

import com.educacionit.delivery.beans.User;
import com.educacionit.delivery.dao.DBConnectionManager;
import com.educacionit.delivery.services.ISecurity;
import com.educacionit.delivery.services.SecurityException;
import com.educacionit.delivery.services.support.SecuritySupport;
import java.util.ArrayList;


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
    
    private ArrayList<Restaurant> getRestaurants(){
        ArrayList<Restaurant> retaurants = new ArrayList<Restaurant>();
        
        
        ///r.
//        while (result.next ()) {
//
//            logger.debug (String.format("Loading User values %s !!! ", u));
//            logger.debug (String.format("User %s found !!! ", u));
//            Restaurant aRestaurant = new Restaurant();
//            
//            user.setAddress (result.getString ("name"));
//            user.setEmail (result.getString ("photo_link"));
//            user.setLastName (result.getString ("last_name"));
//            user.setMobile (result.getString ("mobile"));
//            user.setName (result.getString ("name"));
//            user.setUserName (u);
//            logger.debug (String.format("User values loaded %s ", u));
//
//            break;
//        }
        
        return retaurants;
    }

    @Override
    protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {

            logger.debug (String.format ("Executing loggin user %s from %s", req.getParameter ("username"), req.getRemoteHost ()));
            User u = security.login (req.getParameter ("username"), req.getParameter ("pw"));

            logger.debug (String.format ("User %s from %s was logged !!!", req.getParameter ("username"), req.getRemoteHost ()));
            HttpSession session = req.getSession ();
            session.setAttribute ("user", u);
            ArrayList<Restaurant> allRestaurants = getRestaurants();
            // Insert statistic.
            try {

                DBConnectionManager db = (DBConnectionManager)req.getServletContext().getAttribute ("db");
                Connection conn = db.getConnection ();

                Statement s = conn.prepareStatement ("insert into statistic (id, email, login) values (?, ?, ?)");
                ((PreparedStatement) s).setString (1, session.getId ());
                ((PreparedStatement) s).setString (2, u.getEmail ());
                ((PreparedStatement) s).setTimestamp(3, new java.sql.Timestamp(new Date().getTime()));

                ((PreparedStatement) s).execute ();

            } catch (Exception e) {
                logger.error ("Problems saving statistic => " + e.getMessage ());
            }

            resp.sendRedirect ("home.jsp");

        } catch (SecurityException se) {

            logger.error (se.getMessage ());
            resp.sendRedirect ("index.jsp?err=001");

        } catch (Exception se) {

            logger.error (se.getMessage ());
            resp.sendRedirect ("index.jsp?err=000");
        }
    }
}