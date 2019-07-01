
package com.educacionit.delivery.dao;


import java.sql.Connection;

import org.apache.commons.dbcp2.BasicDataSource;


public class DBConnectionManager {


	private String dbURL;
	private String user;
	private String password;
	private String driver;

    private BasicDataSource dataSource;


	public DBConnectionManager (String url, String u, String p, String d) {

	    super ();

		this.dbURL = url;
		this.user  = u;
		this.password = p;
		this.driver   = d;

		this.setup ();
	}


    private void setup ()  {

        // Create a new Datasource.
        this.dataSource = new BasicDataSource ();

        this.dataSource.setUrl (this.dbURL);
        this.dataSource.setUsername (this.user);
        this.dataSource.setPassword (this.password);
        this.dataSource.setMinIdle (50);
        this.dataSource.setMaxIdle (100);
        this.dataSource.setMaxOpenPreparedStatements (1000);
        this.dataSource.setDriverClassName (this.driver);
    }

	
	public Connection getConnection () {

	    try {

                return this.dataSource.getConnection ();

            } catch (Exception e) {

                throw new DataException(e);
            }
	}
	
	public void closeConnection () {

	    try {

            this.dataSource.close ();

        } catch (Exception e) {

	        e.printStackTrace ();
        }
	}
}