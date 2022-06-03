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
                "       <label>View Employee Information Request</label>\n" +
                "   </div>\n" +
                "   <div id='ViewInfo' class='hiddenForm' hidden>\n" +
                "           <h4><small class='text-muted'>Employee ID:</small> "+user.getID()+"</h4>\n" +
                "           <h4><small class='text-muted'>Name:</small> "+user.getFirstName() + " " + user.getLastName() + "</h4>\n" +
                "           <h4><small class='text-muted'>Email</small> "+user.getEmail()+"</h4>\n" +
                "           <h4><small class='text-muted'>Account Type:</small> "+user.getAccountType()+"</h4>\n"+
                "   </div>\n");
    }

}
