package forms.managerforms;

import Transactions.TransactionDAOImp;
import Transactions.Transactions;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class ViewCompletedReimbursement extends HttpServlet {

    public static void viewCompletedReimbursement(HttpServletRequest req, HttpServletResponse res) throws IOException {
        PrintWriter out = res.getWriter();
        System.out.println("Inside viewCompletedReimbursement");

        DecimalFormat df = new DecimalFormat("###,###,###.00");
        out.println("<table id='transactionsForm' class=\"table table-striped table-hover\">");
        out.println("<tr>" +
                "       <th>Trans ID#</th>" +
                "       <th>Employee Email</th>" +
                "       <th>Reimbursement Note</th>" +
                "       <th>Payout</th>" +
                "       <th>Status</th>" +
                "       <th>Deciding Manager</th>" +
                "       <th>Created</th>" +
                "   </tr>");
        //print out all the accepted and rejected (completed transactions).
        ArrayList<Transactions> t =  TransactionDAOImp.resolvedTransactions();
        try {
            for (Transactions trans : t) {
                out.println("<tr>" +
                        "       <td>" + trans.getTransactionID() + "</td>" +
                        "       <td>" + trans.getEmployeeEmail() + "</td>" +
                        "       <td>" + trans.getProof() + "</td>" +
                        "       <td>$" + df.format(trans.getReimbursementAmount()) + "</td>" +
                        "       <td>" + trans.getStatus() + "</td>" +
                        "       <td>" + trans.getManagerEmail() + "</td>" +
                        "       <td>" + trans.getTimestamp() + "</td>" +
                        "   </tr>");
            }
        }catch (NullPointerException e){
            System.out.println("Manager Resolved Reimbursement" + e.getMessage());
            out.println("<tr><td colspan='5' class='center'>No transactions</td></tr>");
        }
        out.println("</table>");
    }
}
