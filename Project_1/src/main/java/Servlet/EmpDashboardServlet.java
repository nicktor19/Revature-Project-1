package Servlet;

import UsersDao.Users;
import forms.EmpDashboard;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class EmpDashboardServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        Users account = (Users) req.getSession().getAttribute("account"); //get session

        System.out.println("INSIDE DashboardServlet (GET) ");

        req.getRequestDispatcher("template/top_template.html").include(req, res);

        String cssTag ="<link rel='stylesheet' type='text/css' href='css/template.css'>";
        out.println(cssTag);

        //NAV
        if (account.getAccountType().equals("Employee") || account.getAccountType().equals("Manager")) {
            if (account.getAccountType().equals("Manager"))
                out.println("<a href='gmaster' class=\"btn btn-primary active\" role=\"button\" data-bs-toggle=\"button\" aria-pressed=\"true\" style=\"background-color: #1A2238;\">Manager Dashbard</a>   ");
            out.println("<a href='employeedashboard' class=\"btn btn-primary active\" role=\"button\" data-bs-toggle=\"button\" aria-pressed=\"true\" style=\"background-color: #1A2238;\">Employee Dashbard</a>   ");
            out.println("<a href='logout' class=\"btn btn-primary active\" role=\"button\" data-bs-toggle=\"button\" aria-pressed=\"true\" style=\"background-color: #1A2238;\">Logout</a>   ");
        }
        req.getRequestDispatcher("template/mid_template.html").include(req, res);

/**
 * page content start here
 */
        if (req.getSession().getAttribute("welcome") != null) {
            out.println("<div class='success'>"+req.getSession().getAttribute("welcome")+"</div>"); //welcome message

            //print out success page
            String s = (String) req.getSession().getAttribute("FormStatusWriter");
            if (s != null && !s.isEmpty()){
                out.println(s); // print out
                //delete the form success.
                req.getSession().setAttribute("FormStatusWriter", null);
            }

            //load page
            EmpDashboard.employeeFormsLayout("get", req, res);

        } else {
            res.sendRedirect("login");
        }




/**
 * page content ends here
 */

        req.getRequestDispatcher("template/bottom_template.html").include(req, res);
        /* End of template */
    }



    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        Users account = (Users) req.getSession().getAttribute("account"); //get session

        System.out.println("INSIDE DashboardServlet (POST)");

        req.getRequestDispatcher("template/top_template.html").include(req, res);

        String cssTag ="<link rel='stylesheet' type='text/css' href='css/template.css'>";
        out.println(cssTag);

        if (account.getAccountType().equals("Employee") || account.getAccountType().equals("Manager")) {
            if (account.getAccountType().equals("Manager"))
                out.println("<a href='gmaster'>Manager Dashbard</a>   ");
            out.println("<a href='employeedashboard'>Employee Dashbard</a>   ");
            out.println("<a href='logout'>Logout</a>   ");
        }

        req.getRequestDispatcher("template/mid_template.html").include(req, res);

/**
 * page content start here
 */
        if (req.getSession().getAttribute("welcome") != null) {
            out.println("<div class='success'>"+req.getSession().getAttribute("welcome")+"</div>"); //welcome message

            //print out success page
            String s = (String) req.getSession().getAttribute("FormStatusWriter");
            if (s != null && !s.isEmpty()){
                out.println(s); // print out
                //delete the form success.
                req.getSession().setAttribute("FormStatusWriter", null);
            }

            //load page
            EmpDashboard.employeeFormsLayout("post", req, res);

        } else {
            res.sendRedirect("login");
        }

/**
 * page content ends here
 */

        req.getRequestDispatcher("template/bottom_template.html").include(req, res);
        /* End of template */
    }

}
