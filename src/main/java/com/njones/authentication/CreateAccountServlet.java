package com.njones.authentication;

import com.njones.persistence.Database;

import java.io.*;
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
        boolean success;
        Database database = new Database();

        if (householdName.equals("") || firstName.equals("") || lastName.equals("") ||
                email.equals("") || username.equals("") || password.equals("")) {
            success = false;
        } else {
            //Add the user to the database
            if (!database.newUsername(username)) {
                session.setAttribute("message", "The username you chose is not available, " +
                        "please choose another");
                success = false;
            } else {
                database.signUpUser(householdName, firstName, lastName, email, username, password);
                success = true;
            }
        }

        session.setAttribute("success", success);

        response.sendRedirect("signup.jsp");
    }
}
