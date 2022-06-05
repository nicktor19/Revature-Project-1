package forms;

import UsersDao.UserDAOImp;
import UsersDao.Users;
import Transactions.TransactionDAOImp;
import Transactions.Transactions;
import forms.managerforms.AllEmployeeList;
import forms.managerforms.PendingReimbursementForm;
import forms.managerforms.SearchEmpTransactionsForm;
import forms.managerforms.ViewCompletedReimbursement;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

public class ManagerDashboard extends HttpServlet {

    public static void managerFormsLayout(String doType, HttpServletRequest req, HttpServletResponse res) throws IOException {
        HttpSession manager = req.getSession();
        Users account = (Users) manager.getAttribute("account");
        System.out.println(account.toString());
        PrintWriter out = res.getWriter();
        if (doType.equals("get")) {
            //•View reimbursement requests of a specific employee. //post
            out.println("<div id = \"ManagerFormContent\"><!--Top of ManagerForm--->");
            out.println("<div onclick=\"SearchInformation()\" class=\"widget\" style=\"background-color: #1A2238; color: white\">\n" +
                    "    <label class='hover_tab_manager'>Search Employee Reimbursement Requests</label>\n" +
                    "</div>\n"+
                    "<div class =\"hiddenForm\" id=\"SearchHidden\">");
            SearchEmpTransactionsForm.searchEmployeeTransactions(req, res);
            out.println("</div>");

            //reimbursement pending displayed.
            //•Approve/Deny pending reimbursement requests.
            //•View all pending requests of all employees.
           out.println("<div onclick=\"pendingReimbursement()\" class=\"widget\" style=\"background-color: #1A2238; color: white\">\n" +
                    "    <label class='hover_tab_manager'>Pending Reimbursement Request</label>\n" +
                    "</div>\n"+
                    "<div class =\"hiddenForm\" id=\"PendingF\"");
            PendingReimbursementForm.pendingReimbursement(req, res);
            out.println("</div>");

            //•View all resolved requests of all employees.
            out.println("" +
                    "<div onclick=\"CompleteInformation()\" class=\"widget\" style=\"background-color: #1A2238; color: white\">\n" +
                    "    <label class='hover_tab_manager'>Resolved Reimbursement</label>\n" +
                    "</div>\n"+
                    "<div class =\"hiddenForm\" id=\"CompleteHidden\">");
            ViewCompletedReimbursement.viewCompletedReimbursement(req, res);
            out.println("</div>");

            //view all employees
            out.println("<div onclick=\"ViewAllUsers()\" class=\"widget\" style=\"background-color: #1A2238; color: white\">\n" +
                    "    <label class='hover_tab_manager'>View All Users in the System</label>\n" +
                    "</div>\n"+
                    "<div class =\"hiddenForm\" id=\"AllUserHidden\">");
            AllEmployeeList.allEmployeeListDisplay(req, res);
            out.println("</div>");

            out.println("</div><!--Bottom of ManagerForm---></div>");




        } else if (doType.equals("post")) {
            //Brains of the form to submit the response.
            //reimbursement pending displayed.
            //•Approve/Deny pending reimbursement requests.
            //•View all pending requests of all employees.
            System.out.println("inside manager dashboard post results");
            System.out.println(req.getParameter("manager_pending_approval"));
            if (req.getParameter("manager_pending_approval") != null) {
                if (req.getParameter("manager_pending_approval").equals("true") && account.getAccountType().equals("Manager")) {
                    System.out.println("inside manager dashboard post if statment");
                    //update transactions using backend
                    String accountType = req.getParameter("submit_button_approval");
                    if (accountType.equals("Approve"))
                        accountType = "Approved";
                    else if (accountType.equals("Reject"))
                        accountType = "Rejected";
                    int transID = Integer.parseInt(req.getParameter("transID")); // convert to number

                    //set manager_pending_approval to false
                    TransactionDAOImp.ChangeReimbursementStatus(transID, account.getEmail(), accountType);
                    //redirect to dashboard (get)
                    res.sendRedirect("gmaster");
                }
            }

            //•View reimbursement requests of a specific employee. //post
            //create if statement to gather the data.Store the data into the session.
            System.out.println(req.getParameter("manager_employee_search"));
            if (req.getParameter("manager_employee_search") != null) {
                if (req.getParameter("manager_employee_search").equals("true") && account.getAccountType().equals("Manager")) {
                    System.out.println("inside manager dashboard post if statment | entered into manager_employee_search POST");

                    //store the results into session.
                    String email = req.getParameter("search_email");
                    if (!email.isEmpty()) {
                        if (UserDAOImp.UserEmailExists(email)) {
                            ArrayList<Transactions> search = TransactionDAOImp.FindTransByEmpEmail(req.getParameter("search_email"));
                            if (search != null) {
                                req.getSession().setAttribute("Saved_Manager_Search", search);
                            } else {
                                req.getSession().setAttribute("Error_Saved_Manager_Search", "No Transactions For this Employee.");
                                System.out.println(req.getSession().getAttribute("Error_Saved_Manager_Search"));
                            }
                        } else {
                            req.getSession().setAttribute("Error_Saved_Manager_Search", "Could not find any transactions by that employee.");
                            System.out.println(req.getSession().getAttribute("Error_Saved_Manager_Search"));
                        }
                        res.sendRedirect("gmaster");
                    } else {
                        res.sendRedirect("gmaster");
                    }
                }
            }
            if(req.getParameter("manager_employeeName_search") != null){
                if(req.getParameter("manager_employeeName_search").equals("true") && account.getAccountType().equals("Manager")){
                    System.out.println("inside manager dashboard post if statment | entered into manager_User_search POST");
                    //store the results into session.
                    String name_search = req.getParameter("search_name");
                    if(!name_search.isEmpty()){
                        ArrayList<Users> U_search = null;
                        try {
                            U_search = (ArrayList<Users>) UserDAOImp.FindUsers(name_search);

                            if (U_search != null && U_search.size() > 0){
                                req.getSession().setAttribute("saved_employee_search", U_search);
                            }else {
                                req.getSession().setAttribute("Error_saved_employee_search", "No Employee Found.");
                                System.out.println(req.getSession().getAttribute("Error_saved_employee_search"));
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
                res.sendRedirect("gmaster");
            }

        }
    }
}