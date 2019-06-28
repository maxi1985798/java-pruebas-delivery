/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.educacionit.delivery.beans;

import com.educacionit.delivery.servlet.LoginServlet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author u585720
 */
public class RestaurantManager {
    
    private static final Logger logger = Logger.getLogger (LoginServlet.class);
    private static RestaurantManager restaurantManagerInstance = null;
    private ArrayList<Restaurant> restaurantsList = new ArrayList<>();
    
    public static RestaurantManager getInstance() {
        if (restaurantManagerInstance == null) {
            restaurantManagerInstance = new RestaurantManager();
        }
        return restaurantManagerInstance;
    }
    
    public ArrayList<Restaurant> getRestaurants() {
        
        
        return restaurantsList;
    }
}
