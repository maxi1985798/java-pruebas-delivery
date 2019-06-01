package com.educacionit.delivery.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        /*
        // para enviar un texto
        String s = req.getParameter("email");

        PrintWriter out = resp.getWriter();
        out.println("<b>Bienvenido "+s+"</b>");
        out.close();*/

        //String s = req.getParameter("email");
        HttpSession session =  req.getSession();
        session.invalidate();

        //session.setAttribute("email",s);
        // para redireccionar
        //resp.sendRedirect("home.jsp");

    }
}
