
package com.educacionit.delivery.servlet;


import com.educacionit.delivery.dao.DBConnectionManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;




@WebListener
public class AppContextListener implements ServletContextListener {


    public AppContextListener () {

        super ();
    }


    public void contextInitialized (ServletContextEvent servletContextEvent) {
        System.out.println("CONETANDO A LA BASE DE DATOS...");
        DBConnectionManager db = new DBConnectionManager(
                "jdbc:postgresql://localhost/delivery",
                "postgres",
                "123",
                "org.postgrfesql.Driver");
        System.out.println("GUARDANDO DATA SOURCE EN EL CONTEXTO...");
        servletContextEvent.getServletContext().setAttribute("db",db);
    }

    public void contextDestroyed (ServletContextEvent servletContextEvent) {
        DBConnectionManager db = (DBConnectionManager) servletContextEvent.getServletContext().getAttribute("db");
        System.out.println("CERRANDO DATA SOURCE...");
    	db.closeConnection();
    }
}