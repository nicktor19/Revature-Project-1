package forms.employeeforms;

import UsersDao.Users;
import org.jetbrains.annotations.NotNull;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class EmployeeUpdateForm extends HttpServlet {

    static int errorCount = 0;
    static int flagCount = 0;


    /**
     * REGISTER FORM AND METHODS BELOW.
     */

    public static void UserUpdateForm(HttpServletRequest req, HttpServletResponse res) throws IOException {
        PrintWriter out = res.getWriter();
        HttpSession current = req.getSession();
        Users currentUser = (Users) current.getAttribute("account");

            out.println("<div onclick=\"UpdateInformation()\" class='widget'>\n" +
                    "    <label class='hover_tab_emp'>Update Employee Information</label>\n" +
                    "</div>\n" +
                    "<fieldset class='center' id = \"UpdateHidden\" class = 'hiddenForm' hidden>\n" +
                    "    <form action='employeedashboard' method='post'>\n" +
                    "        <table class=\"table table-striped table-hover\">\n" +
                    "            <tr>" +
                    "               <td colspan='2'><input type='hidden' name='employee_update_info' value='true'></td>\n" +
                    "            <tr>\n" +
                    "                <td class='right'><label for='firstname'>First Name: </label></td>\n" +
                    "                <td class='left'><input type='text' name='firstname' maxlength='50' placeholder='Enter First Name' value='"+ currentUser.getFirstName() +"' required></td>\n" +
                    "            </tr>\n" +
                    "            <tr>\n" +
                    "                <td class='right'><label for='lastname'>Last Name: </label></td>\n" +
                    "                <td class='left'><input type='text' name='lastname' maxlength='50' placeholder='Enter Last Name' value='"+ currentUser.getLastName() +"' required></td>\n" +
                    "            </tr>\n" +
                    "            <tr>\n" +
                    "                <td class='right'><label for='email'>Email: </label></td>\n" +
                    "                <td class='left'><input type='email' name='email' maxlength='50' placeholder='Enter Email' value='"+ currentUser.getEmail() +"' disabled></td>\n" +
                    "            </tr>\n" +
                    "            <tr>\n" +
                    "                <td class='right'><label for='oldPassword'>Old Password: </label></td>\n" +
                    "                <td class='left'><input type='password' name='oldPassword' maxlength='20' placeholder='Enter Password' value='' required></td>\n" +
                    "            </tr>\n" +
                    "            <tr>\n" +
                    "               <td class='center' colspan='2'><hr></td>" +
                    "            </tr>\n" +
                    "            <tr>\n" +
                    "                <td class='right'><label for='newpassword'>Password: </label></td>\n" +
                    "                <td class='left'><input type='password' name='newpassword' maxlength='20' placeholder='Optional - Enter Password' value=''></td>\n" +
                    "            </tr>\n" +
                    "            <tr>\n" +
                    "                <td class='right'><label for='password2'>Confirm Password: </label></td>\n" +
                    "                <td class='left'><input type='password' name='newpassword2' maxlength='20' placeholder='Optional - Confirm Password' value=''></td>\n" +
                    "            </tr>\n" +
                    "            <tr>\n" +
                    "                <td class='center' colspan='2'><input type='submit' value='Submit'></td>\n" +
                    "            </tr>\n" +
                    "        </table>\n" +
                    "    </form>\n" +
                    "</fieldset>\n");
    }


    public static void UpdateSuccessMessage(@NotNull HttpServletRequest req, @NotNull HttpServletResponse res) throws IOException {
        PrintWriter out = res.getWriter();

    }


}
