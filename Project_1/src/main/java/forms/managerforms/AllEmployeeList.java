package forms.managerforms;

import Transactions.Transactions;
import UsersDao.UserDAOImp;
import UsersDao.Users;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class AllEmployeeList extends HttpServlet {

    public static void allEmployeeListDisplay(HttpServletRequest req, HttpServletResponse res) throws IOException {
        PrintWriter out = res.getWriter();

        out.println("<table id='transactionsForm' class=\"table table-striped table-hover\">");
        out.println("<tr>" +
                "       <th>Trans ID#</th>" +
                "       <th>First Name</th>" +
                "       <th>Last Name</th>" +
                "       <th>Email</th>" +
                "       <th>Position</th>" +
                "   </tr>");
        //print out all the accepted and rejected (completed transactions).
        try{
            ArrayList<Users> u = (ArrayList<Users>) UserDAOImp.AllUsers();
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
        out.println("</table>");
    }
}
