package sessionmanagementdaoimp;

import UsersDao.Users;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
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
    ArrayList<String> list = new ArrayList<>();
    Users account;
    String loginWelcomeMessage;
    String FormSuccess;

    //constructors
    public RequestSessionManagementDAOImpl(){}
    public RequestSessionManagementDAOImpl(HttpServletRequest req) {
        Enumeration<String> session = req.getSession(true).getAttributeNames();

        while (session.hasMoreElements()) {
            list.add(session.nextElement());
        }
        for (String i : list) {
            switch (i) {
                case "welcome":
                    setLoginWelcomeMessage(i);
                    break;
                case "account":
                    setAccount((Users) req.getSession().getAttribute(i));
                    break;
                case "FormStatusWriter":
                    //setFormSuccess();
                    break;

            }
        }

    }

    //methods
    public ArrayList<String> getList() {
        return list;
    }

    private void setList(ArrayList<String> list) {
        this.list = list;
    }

    public Users getAccount() {
        return account;
    }

    private void setAccount(Users account) {
        this.account = account;
    }

    public String getLoginWelcomeMessage() {
        return loginWelcomeMessage;
    }

    private void setLoginWelcomeMessage(String loginWelcomeMessage) {
        this.loginWelcomeMessage = loginWelcomeMessage;
    }

    public String getFormSuccess() {
        return FormSuccess;
    }
    public void deletedSuccess() {
        FormSuccess = null;
    }
    public void setFormSuccess(String formSuccess) {
        FormSuccess = formSuccess;
    }
}
