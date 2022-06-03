package forms.employeeforms;

import UsersDao.Users;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class EmployeeInfoForm extends HttpServlet {
    public static void employeeInfo(HttpServletRequest req, HttpServletResponse res) throws IOException {
        PrintWriter out = res.getWriter();
        HttpSession current = req.getSession();
        Users user = (Users) current.getAttribute("account");

        out.println("<div onclick='ViewInformation()' class='widget'>\n" +
                "       <label class='hover_tab_emp'>View Employee Information</label>\n" +
                "   </div>\n" +
                "   <div id='ViewInfo' class='hiddenForm' hidden>\n" +
                "           <table class = 'table'>"+
                "           <tr><td><h4><small class='text-muted'>Employee ID:</small></td> <td class='h4'>"+user.getID()+"</h4></td><tr/>\n" +
                "           <tr><td><h4><small class='text-muted'>Name:</small></td> <td class='h4'>"+user.getFirstName() + " " + user.getLastName() + "</h4></td><tr/>\n" +
                "           <tr><td><h4><small class='text-muted'>Email</small></td> <td class='h4'>"+user.getEmail()+"</h4></td><tr/>\n" +
                "           <tr><td><h4><small class='text-muted'>Account Type:</small></td> <td class='h4'>"+user.getAccountType()+"</h4></td><tr/>\n"+
                "           </table></div>\n");
    }

}
