/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.educacionit.delivery.beans;

/**
 *
 * @author u585720
 */
import lombok.*;

@Data
@EqualsAndHashCode
public class Restaurant {
    public static final int enteroLoco = 54;
    public static int getEnteroLoco(){
        return enteroLoco;
    }
    private int id;
    private String name;
    private String phone;
    private String photoLink;
    private String foods;
    private String description;
}
