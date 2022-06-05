package forms.employeeforms;

import UsersDao.Users;
import Transactions.Transactions;
import static Transactions.TransactionDAOImp.getUserNotifyTransactions;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ResolvedReimbursementForm extends HttpServlet {
    public static void resolvedReimbursement(HttpServletRequest req, HttpServletResponse res) throws IOException {
        PrintWriter out = res.getWriter();
        ArrayList<Transactions> trans = getResolved(req);
        out.println("" +
                "<div onclick=\"ResolvedReimbursement()\" class=\"widget\" >\n" +
                "    <label class='hover_tab_emp'>Resolved Reimbursement Request</label>\n" +
                "</div>\n" +
                "<fieldset class='hiddenForm' id=\"ResolvedF\" hidden>\n" +
                "       <table class=\"table table-striped table-hover\">\n" +
                "           <tr>" +
                "               <th>Transaction ID</th>"+
                "               <th>Employee Email</th>"+
                "               <th>Reimbursement Amount</th>"+
                "               <th>Proof Presented</th>"+
                "               <th>Reimbursement Status</th>"+
                "               <th>Reimbursement Handled By</th>"+
                "               <th>Time Stamp</th>"+
                "         </tr>");
        try {
            for (Transactions t : trans) {
                out.println(
                        "<tr>\n" +
                                "<td>" + t.getTransactionID() + "</td>\n" +
                                "<td>" + t.getEmployeeEmail() + "</td>\n" +
                                "<td>" + t.getReimbursementAmount() + "</td>\n" +
                                "<td>" + t.getProof() + "</td>\n" +
                                "<td>" + t.getStatus() + "</td>\n" +
                                "<td>" + t.getManagerEmail() + "</td>\n" +
                                "<td>" + t.getTimestamp() + "</td>\n" +
                                "</tr>\n");
            }
        }catch (NullPointerException e){
            System.out.println("Employee Resolved Reimbursement" + e.getMessage());
            out.println("<tr><td colspan='5' class='center'>No transactions</td></tr>");
        }

        out.println(" </table>\n" +
                "</fieldset>");
    }

    public static ArrayList<Transactions> getResolved(HttpServletRequest req){
        HttpSession current = req.getSession();
        Users cUser = (Users) current.getAttribute("account");
        String email = cUser.getEmail();

        return getUserNotifyTransactions(email, "Not Notified");
    }

}
