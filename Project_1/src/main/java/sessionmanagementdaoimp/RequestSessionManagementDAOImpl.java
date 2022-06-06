package sessionmanagementdaoimp;

import UsersDao.Users;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * Class meant to save any Session request
 * example Account, LoginWelcomeMessage
 * if any data that needs to be stored in the session will be saved here
 * any sessions that need to be deleted should be stored here too.
 */
public class RequestSessionManagementDAOImpl extends HttpServlet {
    //fields
    static Users account;
    static String welcome;
    static String statusFormWriter;


    //constructors
    public RequestSessionManagementDAOImpl(){}
    public RequestSessionManagementDAOImpl(HttpServletRequest req) {
        Enumeration<String> session = req.getSession(true).getAttributeNames();
        ArrayList<RequestSessionManagementDAOImpl> list = new ArrayList<>();
        while (session.hasMoreElements()) {
            String s = session.nextElement();

            switch (s) {
                case "welcome":
                    setWelcome("<div class='success'>Welcome back "+ account.getFirstName() + " " + account.getLastName() +"!</div>");
                    break;
                case "account":
                    setAccount((Users) req.getSession(true).getAttribute(s));
                    break;
                case "FormStatusWriter":
                    setStatusFormWriter((String) req.getSession(true).getAttribute(s));
                    //setFormSuccess();
                    break;
            }
        }
}

    //methods

    public static Users getAccount() {
        return RequestSessionManagementDAOImpl.account;
    }

    private static void setAccount(Users account) {
        account.setPasscode("");
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

    public static String getWelcome() {
        return welcome;
    }

    public static void setWelcome(String welcome) {
        RequestSessionManagementDAOImpl.welcome = welcome;
    }

    public static String getStatusFormWriter() {
        return statusFormWriter;
    }

    public static void setStatusFormWriter(String statusFormWriter) {
        RequestSessionManagementDAOImpl.statusFormWriter = statusFormWriter;
    }
}
