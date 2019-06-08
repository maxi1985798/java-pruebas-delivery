
package com.educacionit.delivery.beans;


import lombok.*;

@Data
@EqualsAndHashCode
public class User {

    private String userName;

    private String name;

    private String lastName;

    private String email;

    private String mobile;

    private String address;

    private String password;
}