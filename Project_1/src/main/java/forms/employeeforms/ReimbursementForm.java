package forms.employeeforms;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ReimbursementForm extends HttpServlet {

    public static void Employee_Reimbursement_Form(HttpServletRequest req, HttpServletResponse res) throws IOException {
        PrintWriter out = res.getWriter();
            out.println("<div onclick='Reimbursement()' class='widget'>\n" +
                    "       <label class='hover_tab_emp'>Submit Reimbursement Request</label>\n" +
                    "    </div>\n" +
                    "    <div id='EmployeeReimburseHidden' class='hiddenForm' hidden>\n" +
                    "       <form action='employeedashboard' method='post'>\n" +
                    "           <input type='hidden' name='Reimbursement_Request' value='true'>" +
                    "           <table class=\"table table-striped table-hover\">\n" +
                    "               <tr>\n" +
                    "                   <td class='right'><label>Reimbursement: </label></td>\n" +
                    "                   <td class='left'><input type='number' step='0.01' name='reimburse_Amount' pattern='money' placeholder='Enter Reimburse Amount' required /></td>\n" +
                    "               </tr>\n" +
                    "               <tr>\n" +
                    "                   <td class='right'><label for=''>Proof: </label></td>\n" +
                    "                   <td class='left'><textarea name='proof' Placeholder='Reimbursement for?'  maxlength='255' value='' rows='4' cols='50' required></textarea></td>\n" +
                    "               </tr>\n" +
                    "                <tr>\n" +
                    "                       <td colspan='2' class='center'><input type='submit' value='Submit Reimbursement Request'/></td>\n" +
                    "                </tr>\n" +
                    "           </table>\n" +
                    "       </form>\n" +
                    "   </div>\n");
    }

}
