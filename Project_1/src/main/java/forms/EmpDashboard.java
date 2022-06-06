package forms;

import UsersDao.Users;
import RequestFormManagementDaoImp.RequestFormManagementDaoImp;
import forms.employeeforms.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class EmpDashboard extends HttpServlet {

    public static void employeeFormsLayout(String doType, HttpServletRequest req, HttpServletResponse res) throws IOException {
        HttpSession employee = req.getSession();
        Users account = (Users) employee.getAttribute("account");
        System.out.println(account.toString());

        if (doType.equals("get")) {
            //•View their information.
            EmployeeInfoForm.employeeInfo(req, res);
            //Go Print out the employeeOption form
            //•Submit a reimbursement req.
            ReimbursementForm.Employee_Reimbursement_Form(req, res);
            //•View their pending reimbursement requests.
            PendingReimbursementForm.pendingReimbursement(req, res);
            //•View their resolved reimbursement requests.
            ResolvedReimbursementForm.resolvedReimbursement(req, res);
            //•Update their information.
            EmployeeUpdateForm.UserUpdateForm(req, res);

        } else if (doType.equals("post")) {
            //acts like the dao
            //get the form and complete the transactions
            if(req.getParameter("Reimbursement_Request") != null)
                if (RequestFormManagementDaoImp.preformReimbursementForm(req, res, account)){
                    employee.setAttribute("FormStatusWriter", "<div class='success' >Your reimbursement has been submitted successfully</div>");
                    res.sendRedirect("employeedashboard");
                } else {
                    employee.setAttribute("FormStatusWriter", "<div class='errors'>Your reimbursement has been submitted successfully</div>");
                    res.sendRedirect("employeedashboard");
                }

            //•Update their information.
            if(req.getParameter("employee_update_info") != null)
                if(RequestFormManagementDaoImp.preformEmployeeUpdateForm(req, res, account)) {
                    employee.setAttribute("FormStatusWriter", "<div class='success'>Your account details were updated</div>");
                    res.sendRedirect("employeedashboard");
                } else {
                    employee.setAttribute("FormStatusWriter", "<div class='errors'>Your account couldn't be updated please try again.</div>");
                    res.sendRedirect("employeedashboard");
                }

        }
    }

}
