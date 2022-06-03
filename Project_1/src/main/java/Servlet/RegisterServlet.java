package Servlet;


import forms.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class RegisterServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        System.out.println("landed on get of register");

        /* Begin of template */
        req.getRequestDispatcher("template/top_template.html").include(req, res);

        String cssTag ="<link rel='stylesheet' type='text/css' href='css/template.css'>";
        out.println(cssTag);

        req.getRequestDispatcher("template/nav_logout.html").include(req, res);

        req.getRequestDispatcher("template/mid_template.html").include(req, res);

/**
 * page content start here
 */

        RegisterForms.registerForm("GET",req, res);

/**
 * page content ends here
 */

        req.getRequestDispatcher("template/bottom_template.html").include(req, res);
        /* End of template */

    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        System.out.println("landed on Post of register");

        /* Begin of template */
        req.getRequestDispatcher("template/top_template.html").include(req, res);

        String cssTag ="<link rel='stylesheet' type='text/css' href='css/template.css'>";
        out.println(cssTag);

        req.getRequestDispatcher("template/nav_logout.html").include(req, res);

        req.getRequestDispatcher("template/mid_template.html").include(req, res);

/**
 * page content start here
 */

        RegisterForms.registerForm("POST",req, res);

/**
 * page content ends here
 */

        req.getRequestDispatcher("template/bottom_template.html").include(req, res);
        /* End of template */
    }
}
