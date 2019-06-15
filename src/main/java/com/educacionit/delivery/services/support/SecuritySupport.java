
package com.educacionit.delivery.services.support;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.log4j.Logger;

import org.apache.commons.codec.binary.Base64;

import com.educacionit.delivery.beans.User;
import com.educacionit.delivery.dao.DBConnectionManager;
import com.educacionit.delivery.services.ISecurity;
import com.educacionit.delivery.services.SecurityException;


public class SecuritySupport implements ISecurity {


    private DBConnectionManager manager;

    private static final Logger logger = Logger.getLogger (SecuritySupport.class);


    public SecuritySupport (DBConnectionManager manager) {

        super ();

        this.manager = manager;
    }


    @Override
    public User login (String u, String p) {

        logger.info (String.format ("Validating user %s", u));

        logger.debug ("Getting one database connection...");
        Connection conn = this.manager.getConnection ();

        User user = null;
        try {

            logger.debug(String.format("Finding user %s", u));
            Statement st = conn.createStatement ();

            Base64 base64 = new Base64 ();
            String pw = new String (base64.encode (p.getBytes ()));

            String query = String.format ("select * from users where user_name='%s' and password='%s'", u, pw);
            logger.debug(String.format("Executing query %s", query));
            ResultSet result = st.executeQuery (query);

            while (result.next ()) {

                logger.debug (String.format("User %s found !!! ", u));
                logger.debug (String.format("Loading User values %s !!! ", u));
                user = new User ();

                user.setAddress (result.getString ("address"));
                user.setEmail (result.getString ("email"));
                user.setLastName (result.getString ("last_name"));
                user.setMobile (result.getString ("mobile"));
                user.setName (result.getString ("name"));
                user.setUserName (u);

                break;
            }

        } catch (Exception e) {
            logger.error("Error "+e.getMessage());
        }

        if (user == null) {

            logger.error (String.format ("User %s not found !!!", u));
            throw new SecurityException ("Usuario y password invalidos !!!");
        } else {

            logger.info (String.format ("User %s validated !!!", u));
            return user;
        }
    }


    @Override
    public void signUp (User u) {
        logger.info(String.format("Singing to user %s...",u));
        logger.debug ("Getting one database connection...");
        Connection conn = this.manager.getConnection ();
        try {
            Statement st = conn.createStatement ();
            Base64 base64 = new Base64 ();
            String pw = new String (base64.encode (u.getPassword().getBytes ()));
            String query = String.format ("  INSERT INTO public.users(\n" +
                            "            user_name, name, last_name, email, mobile, address, password)\n" +
                            "    VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s');",
                    u.getUserName(),
                    u.getName(),
                    u.getLastName(),
                    u.getEmail(),
                    u.getMobile(),
                    u.getAddress(),
                    pw);
            logger.debug(String.format("Executing query %s", query));
            ResultSet result = st.executeQuery (query);


        } catch (Exception e) {
            logger.error("Error "+e.getMessage());
        }
    }
}