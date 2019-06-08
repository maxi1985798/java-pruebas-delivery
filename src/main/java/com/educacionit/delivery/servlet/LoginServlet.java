
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
import java.io.PrintWriter;


@WebServlet ("/login")
public class LoginServlet extends HttpServlet {


    private ISecurity security = new SecuritySupport ();


    @Override
    protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        try {

            User u = security.login (req.getParameter ("email"),
                    req.getParameter ("pw"));

            HttpSession session = req.getSession ();
            session.setAttribute ("user", u);

            resp.sendRedirect ("home.jsp");

        } catch (SecurityException se) {

            resp.sendRedirect ("index.jsp?err=001");
        }
    }
}