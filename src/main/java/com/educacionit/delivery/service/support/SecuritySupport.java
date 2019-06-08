package com.educacionit.delivery.service.support;

import com.educacionit.delivery.beans.User;
import com.educacionit.delivery.service.ISecurity;

public class SecuritySupport implements ISecurity {
    @Override
    public void singUp(User u) {

    }

    @Override
    public User login(String u, String p) {
        if (u.equals("homer@homer.com") && p.equals("123")) {
            User user = new User();
            user.setName("nombre1");
            return user;
        }

        throw new SecurityException("Usuario y password invalidos!!!");
    }
}
