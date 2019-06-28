
package com.educacionit.delivery.servlet;


import java.util.ResourceBundle;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.Logger;

import com.educacionit.delivery.dao.DBConnectionManager;


@WebListener
public class AppContextListener implements ServletContextListener {

    private static final Logger logger = Logger.getLogger (AppContextListener.class);

    public AppContextListener () {

        super ();
    }


    public void contextInitialized (ServletContextEvent servletContextEvent) {

    	logger.info ("Starting Delivery Web Application...");
		ServletContext ctx = servletContextEvent.getServletContext ();


		logger.debug ("Loading context parameters...");
		String url = ctx.getInitParameter ("DBURL");
		String u   = ctx.getInitParameter ("DBUSER");
		String p   = ctx.getInitParameter ("DBPWD");
		String d   = ctx.getInitParameter ("DRIVER");
		String l   = ctx.getInitParameter ("LANG");

		logger.debug (String.format ("Connecting to DB using %s %s %s", url, u, p));
		DBConnectionManager db = new DBConnectionManager (url, u, p, d);

		logger.debug ("Adding connection manager to context...");
		servletContextEvent.getServletContext ().setAttribute ("db", db);


		ResourceBundle bundle = ResourceBundle.getBundle ("locale/message_" + l);
		logger.debug (String.format ("Adding dictionary languaje %s to context...", l));
		servletContextEvent.getServletContext ().setAttribute ("lang", bundle);


		logger.info ("Delivery Web Application Started !!! ");
    }

    public void contextDestroyed (ServletContextEvent servletContextEvent) {

		logger.info ("Releasing Resources From Delivery Web Application Started !!! ");
		DBConnectionManager db = (DBConnectionManager)servletContextEvent.
				getServletContext ().getAttribute ("db");

		logger.debug ("Closing DB Coonections...");
		db.closeConnection ();

		logger.info ("Delivery Web Application Stopped !!! ");
    }
}