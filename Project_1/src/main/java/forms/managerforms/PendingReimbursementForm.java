package forms.managerforms;

import UsersDao.Users;
import Transactions.Transactions;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;

import static Transactions.TransactionDAOImp.*;


public class PendingReimbursementForm extends HttpServlet {

    public static void pendingReimbursement(HttpServletRequest req, HttpServletResponse res) throws IOException {
        PrintWriter out = res.getWriter();

        out.println("<div id='widget'>" +
                "<table id='transactionsForm' class=\"table table-striped table-hover\">" +
                "<th>-</th>" +
                "<th>Transaction #</th>" +
                "<th>Email</th>" +
                "<th>Reimbursement Note</th>" +
                "<th>Amount</th>" +
                "<th>Created On</th>" +
                "<th>Approve</th>" +
                "<th>Reject</th>" +
                "</th>");

        gatherPendingTrans(req, res);

        out.println(
                "</table></select></div>");

    }

    //method to gather the data in the DB and display here.
    //gather data for form.
    public static void gatherPendingTrans(HttpServletRequest req, HttpServletResponse res) throws IOException {
        PrintWriter out = res.getWriter();

        DecimalFormat df = new DecimalFormat("###,###,###.00");
        ArrayList<Transactions> list = pendingTransactions();
        try {
            for (Transactions trans : list) {
                if (!trans.getEmployeeEmail().equals(managerEmailReturn(req))) {
                    out.println("<form action='gmaster' method='post'><tr>" +
                            "<input type='hidden' name='manager_pending_approval' value='true'>" +
                            "<input type='hidden' name='transID' value='"+ trans.getTransactionID() +"'>" +
                            "<td><input type='hidden' name='transactions' value='"+ trans.getTransactionID() +"'></td>" +
                            "<td>" + trans.getTransactionID() +"</td>" +
                            "<td>" + trans.getEmployeeEmail() +"</td>" +
                            "<td>" + trans.getProof() +"</td>" +
                            "<td> $" + df.format(trans.getReimbursementAmount()) +"</td>" +
                            "<td>" + trans.getTimestamp() +"</td>" +
                            "<td><input type='submit' name='submit_button_approval' value='Approve'></td>" +
                            "<td><input type='submit' name='submit_button_approval' value='Reject'></td>" +
                            "</tr></form>");
                }
            }
        } catch (NullPointerException e){
            System.out.println("On file PendingReimbursementForm.java"+ e.getMessage());
            out.println("Currently no reimbursement records to approve...");
        }
    }


    public static String managerEmailReturn(HttpServletRequest req) {
        HttpSession s = req.getSession();
        Users account = (Users) s.getAttribute("account");
        return account.getEmail();
    }

}
