/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.educacionit.delivery.beans;

/**
 *
 * @author u585720
 */
import java.util.ArrayList;
import lombok.*;

@Data
@EqualsAndHashCode
public class Restaurant {
    
    private int id;
    private String name;
    private String phone;
    private String photoLink;
    private String foods;
    private String description;
    

    
}
