
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
import java.sql.ResultSet;
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

    @Override
    protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {

            logger.debug (String.format ("Executing loggin user %s from %s", req.getParameter ("username"), req.getRemoteHost ()));
            User u = security.login (req.getParameter ("username"), req.getParameter ("pw"));

            logger.debug (String.format ("User %s from %s was logged !!!", req.getParameter ("username"), req.getRemoteHost ()));
            HttpSession session = req.getSession ();
            session.setAttribute ("user", u);
            // Insert statistic.
            try {

                DBConnectionManager db = (DBConnectionManager)req.getServletContext().getAttribute ("db");
                Connection conn = db.getConnection ();

                Statement s = conn.prepareStatement ("insert into statistic (id, email, login) values (?, ?, now())");
                logger.debug (String.format ("Getting session id for: %s", req.getParameter ("username")));
                ((PreparedStatement) s).setString (1, session.getId ());
                logger.debug (String.format ("Getting email for: %s", req.getParameter ("username")));
                ((PreparedStatement) s).setString (2, u.getEmail ());
                logger.debug (String.format ("Insert statistic for: %s", req.getParameter ("username")));
                ((PreparedStatement) s).execute ();
                
                // begin get restaurants
                
                ArrayList<Restaurant> allRestaurants = new ArrayList<Restaurant>();
                ArrayList<String> allRestaurantsName = new ArrayList<String>();
                Statement s2 = conn.createStatement();
                String queryToGetRestaurants = String.format ("select * from restaurants");
                logger.debug (String.format ("Executing query: %s", queryToGetRestaurants));
                ResultSet result = s2.executeQuery (queryToGetRestaurants);
                while (result.next ()) {
                    logger.debug (String.format("Restaurant found !!!"));
                    Restaurant r = new Restaurant();
                    r.setName (result.getString ("name"));
                    r.setPhone (result.getString ("phone"));
                    r.setPhotoLink (result.getString ("photo_link"));
                    r.setFoods(result.getString ("foods"));
                    r.setDescription(result.getString ("description"));
                    allRestaurantsName.add(r.getName());
                    allRestaurants.add(r);
                }
                
                req.setAttribute ("restaurants", allRestaurants);
                req.setAttribute ("restaurantsName", allRestaurantsName);
                
                // end get restaurants

            } catch (Exception e) {
                logger.error ("Problems saving statistic => " + e.getMessage ());
            }
            
            RequestDispatcher rd = req.getRequestDispatcher("home.jsp");
            rd.forward(req, resp);
            
        } catch (SecurityException se) {

            logger.error (se.getMessage ());
            resp.sendRedirect ("index.jsp?err=001");

        } catch (Exception se) {

            logger.error (se.getMessage ());
            resp.sendRedirect ("index.jsp?err=000");
        }
    }
}