package Servlet;

import forms.*;
import org.jetbrains.annotations.NotNull;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class LoginServlet extends HttpServlet {

    //redo this whole page.
    public void doGet(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) throws IOException, ServletException {
        System.out.println("/login page (GET) Accessed");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        request.getRequestDispatcher("template/top_template.html").include(request, response);

        String cssTag = "<link rel='stylesheet' type='text/css' href='css/template.css'>";
        out.println(cssTag);

        request.getRequestDispatcher("template/nav_logout.html").include(request, response);

        request.getRequestDispatcher("template/mid_template.html").include(request, response);

/**
 * page content start here
 */

        LoginForm.loginForm("get", request, response);

/**
 * page content ends here
 */

        request.getRequestDispatcher("template/bottom_template.html").include(request, response);
        out.close();
    }

    public void doPost(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response) throws IOException, ServletException {
        System.out.println("/login page (POST) Accessed");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        request.getRequestDispatcher("template/top_template.html").include(request, response);

        String cssTag = "<link rel='stylesheet' type='text/css' href='css/template.css'>";
        out.println(cssTag);

        request.getRequestDispatcher("template/nav_logout.html").include(request, response);

        request.getRequestDispatcher("template/mid_template.html").include(request, response);

/**
 * page content start here
 */
            // logic to create account will be done in the method loginForm.
            LoginForm.loginForm("post", request, response);

/**
 * page content ends here
 */

        request.getRequestDispatcher("template/bottom_template.html").include(request, response);
        //response.sendRedirect("index.html");
        out.close();
    }
}
