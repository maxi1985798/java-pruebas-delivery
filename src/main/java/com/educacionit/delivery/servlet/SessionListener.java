
package com.educacionit.delivery.servlet;


import com.educacionit.delivery.beans.User;
import com.educacionit.delivery.dao.DBConnectionManager;
import org.apache.log4j.Logger;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Date;


@WebListener
public class SessionListener implements HttpSessionListener {


    private static final Logger logger = Logger.getLogger (SessionListener.class);


    public SessionListener() {

        super ();
    }


    public void sessionCreated(HttpSessionEvent sessionEvent) {

        logger.debug (String.format ("Session ID %s Created", sessionEvent.getSession ().getId ()));
    }

    public void sessionDestroyed(HttpSessionEvent sessionEvent) {

        logger.debug (String.format ("Session ID %s Destroyed", sessionEvent.getSession().getId ()));

        DBConnectionManager db = (DBConnectionManager)sessionEvent.getSession().getServletContext().getAttribute ("db");


        // Insert statistic.
        try {

            User user = (User) sessionEvent.getSession().getAttribute("user");

            Connection conn = db.getConnection ();

            Statement s = conn.prepareStatement (String.format ("update statistic set logout=? where id = '%s'", sessionEvent.getSession().getId()));

            ((PreparedStatement) s).setTimestamp(1, new java.sql.Timestamp(new Date().getTime()));
            ((PreparedStatement) s).execute ();


        } catch (Exception e) {

            e.printStackTrace();
            logger.error ("Problems saving statistic => " + e.getMessage ());
        }
    }
}