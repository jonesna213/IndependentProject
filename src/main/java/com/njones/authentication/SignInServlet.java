package com.njones.authentication;

import com.njones.entities.User;
import com.njones.persistence.Database;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(
        name = "signInServlet",
        urlPatterns = { "/signInServlet" }
)

/**
    This servlet class is for Signing in
 */
public class SignInServlet extends HttpServlet {
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
        String username = request.getParameter("uname");
        String password = request.getParameter("password");
        String householdName = request.getParameter("householdName");
        boolean error = false;
        Database database = new Database();
        User user = null;

        if (username.equals("") || password.equals("")) {
            error = true;
        } else {
            user = database.signInUser(username, password, householdName);
            if (user == null) {
                error = true;
            }
        }

        session.setAttribute("error", error);
        session.setAttribute("user", user);

        response.sendRedirect("signin.jsp");
    }
}
