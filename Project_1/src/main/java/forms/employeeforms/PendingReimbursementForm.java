package forms.employeeforms;

import UsersDao.Users;
import Transactions.Transactions;

import static Transactions.TransactionDAOImp.GetUserTypeTrans;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class PendingReimbursementForm extends HttpServlet {

    public static void pendingReimbursement(HttpServletRequest req, HttpServletResponse res) throws IOException {
        PrintWriter out = res.getWriter();

        DecimalFormat df = new DecimalFormat("###,###,###.00"); // format number
        out.println("<div onclick='pendingReimbursement()' class='widget'>\n" +
                "       <label class='hover_tab_emp'>Pending Reimbursement Request</label>\n" +
                "   </div>\n" +
                "   <fieldset class='hiddenForm' id='PendingF' hidden>\n" +
                "       <table class=\"table table-striped table-hover\">\n" +
                "           <tr>\n" +
                "               <th>Trans ID#</th>\n" +
                "               <th>Employee Email</th>\n" +
                "               <th>Amount</th>\n" +
                "               <th>Reimbursement Note</th>\n" +
                "               <th>Time Stamp</th>\n" +
                "          </tr>");


        ArrayList<Transactions> trans = employeePending(req);
        try {
            if (!trans.isEmpty()) {
                for (Transactions t : trans) {
                    out.println("<tr>\n" +
                            "       <td>" + t.getTransactionID() + "</td>\n" +
                            "       <td>" + t.getEmployeeEmail() + "</td>\n" +
                            "       <td>$ " + df.format(t.getReimbursementAmount()) + "</td>\n" +
                            "       <td>" + t.getProof() + "</td>\n" +
                            "       <td>" + t.getTimestamp() + "</td>\n" +
                            "</tr>");
                }
            }
        }catch (NullPointerException e) {
            System.out.println("Employee pending reimbursement" + e.getMessage());
            out.println("<tr><td colspan='5' class='center'>No transactions</td></tr>");
        }

        System.out.println("reached end ");
        out.println(" </table>\n" +
                "</fieldset>");

    }

    public static ArrayList<Transactions> employeePending(HttpServletRequest req) {

        HttpSession user = req.getSession();
        Users current = (Users) user.getAttribute("account");
        String email = current.getEmail();

        return GetUserTypeTrans(email, "Pending");
    }

}
