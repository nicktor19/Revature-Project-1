package Servlet;

import UsersDao.Users;
import forms.ManagerDashboard;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class ManagerDashboardServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        System.out.println("INSIDE DashboardServlet (GET) ");

        req.getRequestDispatcher("template/top_template.html").include(req, res);

        String cssTag ="<link rel='stylesheet' type='text/css' href='css/template.css'>";
        out.println(cssTag);

        //req.getRequestDispatcher("template/nav_loggedin.html").include(req, res);
        out.println("<a href='gmaster' class=\"btn btn-primary active\" role=\"button\" data-bs-toggle=\"button\" aria-pressed=\"true\" style=\"background-color: #1A2238;\">Manager Dashboard</a>" +
                "   <a href='employeedashboard' class=\"btn btn-primary active\" role=\"button\" data-bs-toggle=\"button\" aria-pressed=\"true\" style=\"background-color: #1A2238;\">Employee Dashboard</a> " +
                "  <a href='logout' class=\"btn btn-primary active\" role=\"button\" data-bs-toggle=\"button\" aria-pressed=\"true\" style=\"background-color: #1A2238;\">Logout</a>");
        req.getRequestDispatcher("template/mid_template.html").include(req, res);

/**
 * page content start here
 */
        out.println("<div class='success'>"+req.getSession().getAttribute("welcome")+"</div>"); //welcome message
        HttpSession session = req.getSession();
        Users account = (Users) session.getAttribute("account");
        if (account.getAccountType().equals("Manager"))
            ManagerDashboard.managerFormsLayout("get", req, res);

/**
 * page content ends here
 */

    req.getRequestDispatcher("template/bottom_template.html").include(req, res);
    /* End of template */
}



    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        System.out.println("INSIDE DashboardServlet (POST)");

        req.getRequestDispatcher("template/top_template.html").include(req, res);

        String cssTag ="<link rel='stylesheet' type='text/css' href='css/template.css'>";
        out.println(cssTag);

        out.println("<a href='gmaster' class=\"btn btn-primary active\" role=\"button\" data-bs-toggle=\"button\" aria-pressed=\"true\" style=\"background-color: #1A2238;\">Manager Dashboard</a> " +
                "  <a href='employeedashboard' class=\"btn btn-primary active\" role=\"button\" data-bs-toggle=\"button\" aria-pressed=\"true\" style=\"background-color: #1A2238;\">Employee Dashboard</a> " +
                "  <a href='logout' class=\"btn btn-primary active\" role=\"button\" data-bs-toggle=\"button\" aria-pressed=\"true\" style=\"background-color: #1A2238;\">Logout</a>");

        req.getRequestDispatcher("template/mid_template.html").include(req, res);

/**
 * page content start here
 */
        out.println("<div class='success'>"+req.getSession().getAttribute("welcome")+"</div>");

        HttpSession session = req.getSession();
        Users account = (Users) session.getAttribute("account");
        if (account.getAccountType().equals("Manager"))
            ManagerDashboard.managerFormsLayout("post", req, res);

/**
 * page content ends here
 */

        req.getRequestDispatcher("template/bottom_template.html").include(req, res);
        /* End of template */
    }

}
