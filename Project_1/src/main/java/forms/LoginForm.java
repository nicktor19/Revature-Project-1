package forms;

import UsersDao.UserDAOImp;
import UsersDao.Users;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

import static forms.RegisterForms.formInput;
import static forms.RegisterForms.regSuccessMessage;

public class LoginForm extends HttpServlet {
    static int errorCount = 0;
    static int flagCount = 0;


    /**
     * LOGIN FORM AND METHODS
     */
    public static void loginForm(String doType, HttpServletRequest req, HttpServletResponse res) throws IOException {
        PrintWriter out = res.getWriter();

        doType = doType.toLowerCase();
        if (doType.equals("get")) {
            out.println("<div id='JSEraser'>");
            regSuccessMessage(req, res);
            out.println("</div>");

            out.println("<fieldset>\n" +
                    "       <h1>Login</h1>\n" +
                    "       <div class='FormContainer'>\n" +
                    "           <form action='login' method='post'>\n" +
                    "               <div class='mb-3' required>\n" +
                    "                   <label class='form-label'>Employee Email</label>\n" +
                    "                   <input type='email' name='email' placeholder='Employee Email' class='form-control' required>\n" +
                    "               </div>\n" +
                    "               <div class='mb-3'>\n" +
                    "                   <label class='form-label'>Password</label>\n" +
                    "                   <input type='password' name='password' maxlength='20' placeholder='Enter Password' value='' class='form-control' required>\n" +
                    "               </div>\n" +
                    "                    <input type='submit' value='Login' class='btn btn-primary'>\n" +
                    "           </form>\n" +
                    "       </div>\n"+
                    "   </fieldset>");
        } else if (doType.equals("post")) {
            out.println("<fieldset>\n" +
                    "       <h1>Login</h1>\n" +
                    "       <div class='FormContainer'>\n" +
                    "           <form action='login' method='post' >\n" +
                    "               <div class='mb-3' required>\n" +
                    "                   <label class='form-label'>Employee Email: <b class= 'errors'>" + loginValidation("email", req, res) + "</b></label>\n" +
                    "                   <input type='email' name='email' placeholder='Employee Email' class='form-control' required>\n" +
                    "               </div>\n" +
                    "               <div class='mb-3' required>\n" +
                    "                   <label class='form-label'>Password: <b class='errors'>"+ loginValidation("password", req, res) +"</b></label>\n" +
                    "                   <input type='password' name='password' maxlength='20' placeholder='Enter Password' value='' class='form-control' required>\n" +
                    "               </div>\n" +
                    "                   <input type='submit' value='Login' class='btn btn-primary'>\n" +
                    "           </form>\n" +
                    "       </div>\n" +
                    "   </fieldset>");
            out.println("<div class='errors' id='JSEraser'>" + loginAttempt(req, res) + "</div>");
        }
        if (flagCount > 0 || errorCount > 0) {
            errorCount = 0;
            flagCount = 0;
        }
        out.close();
    }

    public static String loginValidation(String field, HttpServletRequest req, HttpServletResponse res) throws IOException {
        PrintWriter out = res.getWriter();
        String eMessage = "";
        // if input is empty
        field = formInput(field, req);

        flagCount++;
        if (field.isEmpty()) {
            errorCount++;
            eMessage += "Cant be empty.";
            return eMessage;
        }
        return eMessage;
    }

    public static String loginAttempt(HttpServletRequest req, HttpServletResponse res) throws IOException {
        if (errorCount == 0 && flagCount == 2) {
            //attempt to login.
            Users emp = UserDAOImp.login(req.getParameter("email"), req.getParameter("password"));
            if(emp != null) {
                if (emp.getEmail() != null) {
                    //created the session here
                    HttpSession loginSuccess = req.getSession();
                    loginSuccess.setAttribute("account", emp);

                    if (emp.getAccountType().equals("Manager")){
                    //System.out.println(loginSuccess.getAttribute("account"));
                        loginSuccess.setAttribute("welcome", "Welcome back " + emp.getFirstName() + " " + emp.getLastName() + " !");
                        res.sendRedirect("gmaster");
                    } else if (emp.getAccountType().equals("Employee")) {
                        loginSuccess.setAttribute("welcome", "Welcome back " + emp.getFirstName() + " " + emp.getLastName() + " !");
                        res.sendRedirect("employeedashboard");
                    }
                } else {
                    errorCount++;
                    return "Please confirm your account credentials.";
                }
            }else {
                errorCount++;
                return "Please confirm your account credentials.";
            }
        }
        return "";
    }
}
