package Servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class IndexServlet extends HttpServlet {

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        if ((req.getSession(false) != null)) {  //check if it has an object of users
            //redirects to profile if logged in already
            //check if username exist
            res.sendRedirect("employeedashboard");
        } else {
            req.getRequestDispatcher("index.html").include(req, res);
        }
        out.close();
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        if (req.getSession(false) != null){ //check if has an object of users
            //redirects to profile if logged in already
            //check if username exist
            res.sendRedirect("employeedashboard");
        } else {
            req.getRequestDispatcher("index.html").include(req, res);
        }
        out.close();
    }

}