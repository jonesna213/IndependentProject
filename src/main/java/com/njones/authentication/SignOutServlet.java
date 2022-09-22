package com.njones.authentication;

import com.njones.persistence.Database;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(
        name = "signOutServlet",
        urlPatterns = { "/signOutServlet" }
)

/**
 This servlet class is for Signing out
 */
public class SignOutServlet extends HttpServlet {
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
        String choice = request.getParameter("choice");
        if (choice.equals("Sign Out")) {
            session.removeAttribute("user");
        }

        response.sendRedirect("index.jsp");
    }
}
