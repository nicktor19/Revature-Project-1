package forms;

import UsersDao.UserDAOImp;
import org.jetbrains.annotations.NotNull;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class RegisterForms extends HttpServlet {
    static int errorCount = 0;
    static int flagCount = 0;


    /**
     * REGISTER FORM AND METHODS BELOW.
     */

    public static void registerForm(String doType, HttpServletRequest req, @NotNull HttpServletResponse res) throws IOException {
        PrintWriter out = res.getWriter();

        doType = doType.toLowerCase();
        if (doType.equals("get")) {
            out.println("<fieldset>\n" +
                    "    <h1>Register Account</h1>\n" +
                    "    <div class=\"FormContainer\">" +
                    "    <form action='register' method='post'>\n" +
                    "            <div class=\"mb-3\">\n" +
                    "                <label for='firstname' class=\"form-label\">First Name: </label>\n" +
                    "              <input type='text' name='firstname' maxlength='50' placeholder='Enter First Name' value='' class=\"form-control\" required>\n" +
                    "            </div>\n" +
                    "            <div class=\"mb-3\">" +
                    "                <label for='lastname' class=\"form-label\">Last Name: </label>\n" +
                    "                <input type='text' name='lastname' maxlength='50' placeholder='Enter Last Name' value='' class=\"form-control\" required>\n" +
                    "            </div>\n" +
                    "            <div class=\"mb-3\">\n" +
                    "                <label for='email' class=\"form-label\">Email: </label>\n" +
                    "                <input type='email' name='email' maxlength='50' placeholder='Enter Email' value='' class=\"form-control\" required>\n" +
                    "            </div class=\"mb-3\">\n" +
                    "            <div>\n" +
                    "                <label for='password' class=\"form-label\">Password: </label>\n" +
                    "               <input type='password' name='password' maxlength='20' placeholder='Enter Password' value='' class=\"form-control\" required>\n" +
                    "            </div class=\"mb-3\">\n" +
                    "            <div class=\"mb-3\">\n" +
                    "                <label for='password2' class=\"form-label\">Confirm Password: </label>\n" +
                    "               <input type='password' name='password2' maxlength='20' placeholder='Confirm Password' value='' class=\"form-control\" required>\n" +
                    "            </div>\n" +
                    "            <div class=\"mb-3\">\n" +
                    "               <input type='submit' value='Submit' class=\"btn btn-primary\">\n" +
                    "            </div>\n" +
                    "    </form>\n" +
                    "    </div>" +
                    "</fieldset>");
        } else if (doType.equals("post")) {
            out.println("<fieldset>\n" +
                    "    <h1>Register Account</h1>\n" +
                    "    <div class=\"FormContainer\">" +
                    "    <form action='register' method='post'>\n" +
                    "            <div class=\"mb-3\">\n" +
                    "                <label for='firstname' class=\"form-label\">First Name: <b class= 'errors'>" + registerValidation("firstname", req, res) + "</b></label>\n" +
                    "              <input type='text' name='firstname' maxlength='50' placeholder='Enter First Name' value='' class=\"form-control\" required>\n" +
                    "            </div>\n" +
                    "            <div class=\"mb-3\">" +
                    "                <label for='lastname' class=\"form-label\">Last Name: <b class= 'errors'>" + registerValidation("lastname", req, res) + "</b></label>\n" +
                    "                <input type='text' name='lastname' maxlength='50' placeholder='Enter Last Name' value='' class=\"form-control\" required>\n" +
                    "            </div>\n" +
                    "            <div class=\"mb-3\">\n" +
                    "                <label for='email' class=\"form-label\">Email: <b class= 'errors' id='JSEraser'>" + registerValidation("email", req, res) + "</b></label>\n" +
                    "                <input type='email' name='email' maxlength='50' placeholder='Enter Email' value='' class=\"form-control\" required>\n" +
                    "            </div class=\"mb-3\">\n" +
                    "            <div>\n" +
                    "                <label for='password' class=\"form-label\">Password: <b class= 'errors'>" + registerValidation("password", req, res) + "</b></label>\n" +
                    "               <input type='password' name='password' maxlength='20' placeholder='Enter Password' value='' class=\"form-control\" required>\n" +
                    "            </div class=\"mb-3\">\n" +
                    "            <div class=\"mb-3\">\n" +
                    "                <label for='password2' class=\"form-label\">Confirm Password:<b class= 'errors'>" + registerValidation("password2", req, res) + "</b></label>\n" +
                    "               <input type='password' name='password2' maxlength='20' placeholder='Confirm Password' value='' class=\"form-control\" required>\n" +
                    "            </div>\n" +
                    "            <div class=\"mb-3\">\n" +
                    "               <input type='submit' value='Submit' class=\"btn btn-primary\">\n" +
                    "            </div>\n" +
                    "    </form>\n" +
                    "    </div>" +
                    "</fieldset>");
            if (errorCount > 0 || flagCount > 0) {// reset error counts
                errorCount = 0;
                flagCount = 0;
            }
        }
        out.close();
    }

    /**
     * Universal Form input checks if the input is empty and returns the string entered.
     */
    public static String formInput(String field, @NotNull HttpServletRequest req) {

        if (!req.getParameter(field).isEmpty() && req.getParameter(field) != null) {
            return req.getParameter(field);
        }
        return "";
    }

    public static String registerValidation(String field, HttpServletRequest req, HttpServletResponse res) throws IOException {
        flagCount++;
        String eMessage = "";
        if (formInput(field, req).equals("")) {
            errorCount++;
            eMessage += "Can't be empty.";
        }

        if (field == "email") {
            //check if password already exist, if false then create
            //need backend email valid method for users.
            if (UserDAOImp.UserEmailExists(req.getParameter(field))) {
                errorCount++;
                eMessage += "Email Already exist, please login or change.";
            }
        }

        if (field.equals("password")) {
            if (!formInput(field, req).equals(formInput("password2", req))) {
                errorCount++;
                eMessage += " | Passwords don't match";
            }
        }

        if (errorCount > 0)
            return eMessage;

        if (errorCount == 0 && flagCount == 5) {
            System.out.println("Users can be created");
            //send req to backend to create account
            String lastn = req.getParameter("lastname");
            //uppercase
            lastn = lastn.substring(0, 1).toUpperCase() + lastn.substring(1).toLowerCase();
            String firstn = req.getParameter("firstname");
            //uppercase
            firstn = firstn.substring(0, 1).toUpperCase() + firstn.substring(1).toLowerCase();
            String email = req.getParameter("email");
            String password = req.getParameter("password");

            UserDAOImp.Register(lastn, firstn, email, password);
            //if created message
            HttpSession message = req.getSession();
            message.setAttribute("success", "Congratulations! Account Created, Please Login.");
            res.sendRedirect("login");//redirect to login page.

        }
        return "";
    }

    public static void regSuccessMessage(@NotNull HttpServletRequest req, @NotNull HttpServletResponse res) throws IOException {
        PrintWriter out = res.getWriter();
        if (!req.getSession(true).isNew()) {
            HttpSession accountCreated = req.getSession();
            String successMessage = (String) accountCreated.getAttribute("success");


            if (!successMessage.isEmpty()) {
                System.out.println(successMessage);
                out.println("<div class='success'>" + successMessage + "</div>");
                accountCreated.removeAttribute("success");
                accountCreated.invalidate();

            }
        }
    }
}
