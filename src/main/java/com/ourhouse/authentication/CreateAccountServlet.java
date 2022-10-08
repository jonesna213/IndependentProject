package com.ourhouse.authentication;

import com.ourhouse.entity.Household;
import com.ourhouse.entity.User;
import com.ourhouse.persistence.GenericDao;
import com.ourhouse.persistence.Passwords;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(
        name = "createAccountServlet",
        urlPatterns = { "/createAccountServlet" }
)

/**
    This servlet class is for creating an account.
 */
public class CreateAccountServlet extends HttpServlet {
    private final Logger logger = LogManager.getLogger(this.getClass());
    /**
     *  Handles HTTP POST requests.
     *
     *@param  request                   the HttpServletRequest object
     *@param  response                   the HttpServletResponse object
     *@exception  ServletException  if there is a Servlet failure
     *@exception  IOException       if there is an IO failure
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String householdName = request.getParameter("householdName");
        String firstName = request.getParameter("fname");
        String lastName = request.getParameter("lname");
        String email = request.getParameter("email");
        String username = request.getParameter("uname");
        String password = request.getParameter("password");
        boolean success = true;
        GenericDao<User> userDao = new GenericDao<>(User.class);
        GenericDao<Household> householdDao = new GenericDao<>(Household.class);

        if (householdName.equals("") || firstName.equals("") || lastName.equals("") ||
                email.equals("") || username.equals("") || password.equals("")) {
            success = false;
        } else {
            //Add the user to the database
            if (householdDao.getByPropertyEqual("householdName", householdName).size() != 0) {
                session.setAttribute("message", "The username you chose is not available, " +
                        "please choose another");
                success = false;
            } else if (userDao.getByPropertyEqual("username", username).size() != 0) {
                session.setAttribute("message", "The household name you chose is not available, " +
                        "please choose another");
                success = false;
            } else {
                try {
                    Passwords genPassword = new Passwords();
                    List<String> passwordStuff = genPassword.getPassword(password);

                    Household newHousehold = new Household();
                    newHousehold.setHouseholdName(householdName);
                    newHousehold.setSalt(passwordStuff.get(0));
                    newHousehold.setPasswordHash(passwordStuff.get(1));

                    User newUser = new User();
                    newUser.setFirstName(firstName);
                    newUser.setLastName(lastName);
                    newUser.setUsername(username);
                    newUser.setPermissions("admin");
                    newUser.setEmail(email);

                    newHousehold.addMember(newUser);

                    householdDao.insert(newHousehold);
                } catch (NoSuchAlgorithmException e) {
                    logger.error("Password gen problem", e);
                    success = false;
                }
            }
        }

        session.setAttribute("success", success);

        response.sendRedirect("signup.jsp");
    }
}
