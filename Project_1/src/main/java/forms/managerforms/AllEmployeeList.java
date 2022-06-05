package forms.managerforms;

import Transactions.Transactions;
import UsersDao.UserDAOImp;
import UsersDao.Users;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class AllEmployeeList extends HttpServlet {

    public static void allEmployeeListDisplay(HttpServletRequest req, HttpServletResponse res) throws IOException {
        PrintWriter out = res.getWriter();

        out.println("<div id= 'searchBar'><form action='gmaster' method='post' id='ajax'>" +
                "       <label for='search_name'>Search Employees by Name: </label>" +
                "       <input type='hidden' name='manager_employeeName_search' value='true'>" +
                "       <input type='text' name='search_name' placeholder='Search Users' alt='Enter name of the employee' required>" +
                "       <input type='submit' value='Search'>" +
                "   </form></div>");

        String error = null;

        out.println("<div id='searchResult'>\n" +
                "<!-- top Search -->" +
                "<table id='transactionsForm' class=\"table table-striped table-hover\">");

        try{
            HttpSession session = req.getSession(true);
            error = (String) req.getSession().getAttribute("Error_saved_employee_search");
            if(error != null){
                out.println("<div class='errors' id = \"JSEraser\">"+ session.getAttribute("Error_saved_employee_search")+" \n Please check full list below</div>");
                //destory this session
                session.setAttribute("Error_saved_employee_search", null);
            }
        }catch (NullPointerException e){
            System.out.println("Error om search emp line 33 " + e.getMessage());
        }

        out.println("<tr>" +
                "       <th>Trans ID#</th>" +
                "       <th>First Name</th>" +
                "       <th>Last Name</th>" +
                "       <th>Email</th>" +
                "       <th>Position</th>" +
                "   </tr>");
        //print out all the accepted and rejected (completed transactions).
        try{
            HttpSession session = req.getSession(true);
            ArrayList<Users> u;
            if(session.getAttribute("saved_employee_search") != null) {
                u = (ArrayList<Users>) session.getAttribute("saved_employee_search");
                session.setAttribute("saved_employee_search", null);

            }
            else {
                u = (ArrayList<Users>) UserDAOImp.AllUsers();
            }
            for (Users users : u) {
                out.println("<tr>" +
                        "       <td>"+ users.getID() +"</td>" +
                        "       <td>"+ users.getFirstName() +"</td>" +
                        "       <td>"+ users.getLastName()  +"</td>" +
                        "       <td>"+ users.getEmail() +"</td>" +
                        "       <td>"+ users.getAccountType() +"</td>" +
                        "   </tr>");
            }
        } catch (NullPointerException e){
            System.out.println("AllEmployeeList.java"+ e.getMessage());
            out.println("<tr><td class='center'> Currently no Employees... </td></tr>");
        }
        out.println("</table><!-- bottom Search --></div>");
    }
}
