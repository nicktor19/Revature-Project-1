package sessionmanagementdaoimp;

import UsersDao.Users;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

/**
 * Class meant to save any Session request
 * example Account, LoginWelcomeMessage
 * if any data that needs to be stored in the session will be saved here
 * any sessions that need to be deleted should be stored here too.
 */
public class RequestSessionManagementDAOImpl extends HttpServlet {
    //fields
    static Users account;


//    //constructors
//    public RequestSessionManagementDAOImpl(){}
//    public RequestSessionManagementDAOImpl(HttpServletRequest req) {
//        Enumeration<String> session = req.getSession(true).getAttributeNames();
//
//        while (session.hasMoreElements()) {
//            list.add(session.nextElement());
//        }
//        for (String i : list) {
//            switch (i) {
//                case "welcome":
//                    setLoginWelcomeMessage(i);
//                    break;
//                case "account":
//                    setAccount((Users) req.getSession(true).getAttribute(i));
//                    break;
//                case "FormStatusWriter":
//                    //setFormSuccess();
//                    break;
//
//            }
//        }
//}

    //methods

    public static Users getAccount() {
        return RequestSessionManagementDAOImpl.account;
    }

    private static void setAccount(Users account) {
        RequestSessionManagementDAOImpl.account = account;
    }
    public static Users sessionStarterEmp(HttpServletRequest req, HttpServletResponse res) throws IOException {
        try{
            setAccount((Users) req.getSession(true).getAttribute("account"));
            Users a = RequestSessionManagementDAOImpl.getAccount();
            if (a != null && (a.getAccountType().equals("Employee") || a.getAccountType().equals("Manager"))) {
                return a;
            }
        } catch (NullPointerException e) {
            res.sendRedirect("logout");
        }
        return null;
    }

    public static Users sessionStarterMan(HttpServletRequest req, HttpServletResponse res) throws IOException {
        try{
            setAccount((Users) req.getSession(true).getAttribute("account"));
            Users a = RequestSessionManagementDAOImpl.getAccount();
            if (a != null && a.getAccountType().equals("Manager")) {
                return a;
            }
        } catch (NullPointerException e) {
            res.sendRedirect("logout");
        }
        return null;
    }
}
