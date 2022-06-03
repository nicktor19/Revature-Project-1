package RequestFormManagementDaoImp;


import UsersDao.UserDAOImp;
import UsersDao.Users;
import Transactions.TransactionDAOImp;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Class meant to save any form request
 * example Employee ReimbursementForm.
 * if any data that needs to be stored in the session will be saved here
 * any sessions that need to be deleted should be stored here too.
 */
public class RequestFormManagementDaoImp extends HttpServlet {
    //constructors
    public RequestFormManagementDaoImp() {}
    //methods
    /**
     * formsDAOImp
     */
    public static boolean preformReimbursementForm(HttpServletRequest req, HttpServletResponse res, Users account) {
        if (req.getParameter("Reimbursement_Request") != null &&
                req.getParameter("Reimbursement_Request").equals("true")) {

            double a = Double.parseDouble(req.getParameter("reimburse_Amount"));
            String p = req.getParameter("proof");
            TransactionDAOImp.ReimbursementReq(account.getEmail(), a, p);
            return true;
        }
        return false;
    }

    public static boolean preformEmployeeUpdateForm(HttpServletRequest req, HttpServletResponse res, Users account) {
        if (req.getParameter("employee_update_info") != null &&
                req.getParameter("employee_update_info").equals("true")) {

            String firstName = req.getParameter("firstname");
            String lastName = req.getParameter("lastname");
            String email = req.getParameter("lastname");
            String oldPassword = req.getParameter("oldPassword");
            String newpassword1 = req.getParameter("newpassword");
            String newpassword2 = req.getParameter("newpassword2");
            if (newpassword1.equals(newpassword2)) {
                //save into account
                HttpSession session = req.getSession();

                session.setAttribute("account", UserDAOImp.UpdateUserAccount(account, firstName, lastName, oldPassword, newpassword1));

                return true;
            }
        }
        return false;
    }
}
