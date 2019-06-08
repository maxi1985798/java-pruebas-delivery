
package com.educacionit.delivery.services;


import com.educacionit.delivery.beans.User;


public interface ISecurity {


    User login (String u, String p);

    void singUp(User u);
}