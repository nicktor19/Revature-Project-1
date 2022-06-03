package forms.managerforms;

import Transactions.Transactions;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;

import static Transactions.TransactionDAOImp.pendingTransactions;
import static forms.managerforms.PendingReimbursementForm.managerEmailReturn;

public class SearchEmpTransactionsForm extends HttpServlet {

    public static void searchEmployeeTransactions(HttpServletRequest req, HttpServletResponse res) throws IOException {
        PrintWriter out = res.getWriter();

        //use FindTransByEmpEmail method
        //Create form : Search Employee data.
        //manager_employee_search == true
        //out.println("inside...");
        out.println("<div id= 'searchBar'><form action='gmaster' method='post' id='ajax'>" +
                "       <label for='search_email'>Search Employee Transactions: </label>" +
                "       <input type='hidden' name='manager_employee_search' value='true'>" +
                "       <input type='email' name='search_email' placeholder='Search transactions' alt='Enter Email of the employee' required>" +
                "       <input type='submit' value='Search'>" +
                "   </form></div>");

        //print out results form sessions:
        HttpSession session = req.getSession(true);
        ArrayList<Transactions> trans = (ArrayList<Transactions>) session.getAttribute("Saved_Manager_Search");
        String error = (String) req.getSession().getAttribute("Error_Saved_Manager_Search");
        //new display
        out.println("<div id='searchResult'>" +
                "<!-- top Search --><table id='transactionsForm' class=\"table table-striped table-hover\">" +
                "<th>-</th>" +
                "<th>Transaction #</th>" +
                "<th>EmpDashboardServlet Email</th>" +
                "<th>Amount</th>" +
                "<th>Status</th>" +
                "<th>Created On</th>" +
                "<th>Approve</th>" +
                "<th>Reject</th>" +
                "</th>");

        gatherEmployeeTransactions(req, res);

        out.println(
                "</table></select><!-- bottom Search --></div>");

        System.out.println("inside transactions forms for search");
        System.out.println(trans);
        out.println();
    }

    public static void gatherEmployeeTransactions(HttpServletRequest req, HttpServletResponse res) throws IOException {
        PrintWriter out = res.getWriter();

        DecimalFormat df = new DecimalFormat("###,###,###.00");
        HttpSession session = req.getSession(true);
        //print out error message in a div
        if (session.getAttribute("Error_Saved_Manager_Search") != null){
            out.println("<div class='errors'>"+ session.getAttribute("Error_Saved_Manager_Search")+"</div>");
            //destory this session
            session.setAttribute("Error_Saved_Manager_Search", null);
        }

        if (session.getAttribute("Saved_Manager_Search") != null) {
            ArrayList<Transactions> list = (ArrayList<Transactions>) session.getAttribute("Saved_Manager_Search");

            for (Transactions trans : list) {
                if (!trans.getEmployeeEmail().equals(managerEmailReturn(req))) {
                    out.println("<form action='gmaster' method='post' id='ajax2'><tr>" +
                            "<input type='hidden' name='manager_pending_approval' value='true'>" +
                            "<input type='hidden' name='transID' value='" + trans.getTransactionID() + "'>" +
                            "<td><input type='hidden' name='transactions' value='" + trans.getTransactionID() + "'></td>" +
                            "<td>" + trans.getTransactionID() + "</td>" +
                            "<td>" + trans.getEmployeeEmail() + "</td>" +
                            "<td> $" + df.format(trans.getReimbursementAmount()) + "</td>" +
                            "<td>" + trans.getStatus() + "</td>" +
                            "<td>" + trans.getTimestamp() + "</td>");

                    if (trans.getStatus().equals("Pending")) {
                        out.println(
                                "<td><input type='submit' name='submit_button_approval' value='Approve'></td>" +
                                        "<td><input type='submit' name='submit_button_approval' value='Reject'></td>");
                    } else {
                        out.println("<td></td>" +
                                "<td></td>");
                    }
                    out.println("</tr></form>");
                    trans = null;
                    session.setAttribute("Saved_Manager_Search", null);
                }
            }
        }
    }

}