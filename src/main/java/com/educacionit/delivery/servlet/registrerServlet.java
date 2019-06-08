
package com.educacionit.delivery.servlet;


import com.educacionit.delivery.beans.User;
import com.educacionit.delivery.services.ISecurity;
import com.educacionit.delivery.services.SecurityException;
import com.educacionit.delivery.services.support.SecuritySupport;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet ("/register")
public class registrerServlet extends HttpServlet {


    private ISecurity security = new SecuritySupport ();


    @Override
    protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        try {

            User u = new User();
            u.setUserName(req.getParameter("userName"));
            u.setName(req.getParameter("name"));
            u.setLastName(req.getParameter("lastName"));
            u.setEmail(req.getParameter("email"));
            u.setMobile(req.getParameter("mobile"));
            u.setAddress(req.getParameter("address"));
            u.setPassword(req.getParameter("pw"));

            if ( u.getAddress().equals("") ||
                    u.getEmail().equals("") ||
                    u.getEmail().equals("") ||
                    u.getMobile().equals("")) {
                resp.sendRedirect("register.jsp?err=002");
            }

            if (!u.getPassword().equals(req.getParameter("pw2"))) {
                resp.sendRedirect("register.jsp?err=003");
            }

            security.singUp(u);


            HttpSession session = req.getSession ();
            session.setAttribute ("user", u);

            resp.sendRedirect ("home.jsp");

        } catch (SecurityException se) {

            resp.sendRedirect ("register.jsp?err=001");
        }
    }
}