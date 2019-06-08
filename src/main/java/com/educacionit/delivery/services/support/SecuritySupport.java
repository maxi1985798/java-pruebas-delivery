
package com.educacionit.delivery.services.support;


import com.educacionit.delivery.beans.User;
import com.educacionit.delivery.services.ISecurity;
import com.educacionit.delivery.services.SecurityException;


public class SecuritySupport implements ISecurity {

    @Override
    public User login (String u, String p) {

        if (u.equals ("homer@gmail.com") && p.equals ("123")) {

            User user = new User ();

            user.setName ("Homer");
            user.setLastName ("Simpson");

            return user;
        }

        throw new SecurityException ("Usuario y password invalidos !!!");
    }

    @Override
    public void singUp(User u) {

    }
}
