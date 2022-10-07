package com.ourhouse.authentication;

import com.ourhouse.entity.Household;
import com.ourhouse.entity.User;
import com.ourhouse.persistence.Database;
import com.ourhouse.persistence.GenericDao;

import java.io.*;
import java.util.List;
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
        GenericDao<User> userDao = new GenericDao<>(User.class);
        GenericDao<Household> householdDao = new GenericDao<>(Household.class);
        User user = null;

        if (username.equals("") || password.equals("")) {
            error = true;
        } else {
            Passwords genPassword = new Passwords();
            List<User> possibleUser = userDao.getByPropertyEqual("username", username);
            List<Household> possibleHousehold = householdDao.getByPropertyEqual("householdName", householdName);

            if (possibleHousehold.size() != 0 && possibleUser.size() != 0) {
                User signInUser = possibleUser.get(0);
                Household signInHousehold = possibleHousehold.get(0);
                String passwordSalt = signInHousehold.getSalt();
                String passwordHash = genPassword.getPasswordHash(password, passwordSalt);

                if (signInUser.getHousehold().getId() == signInHousehold.getId() && signInHousehold.getPasswordHash().equals(passwordHash)) {
                    user = signInUser;
                }
            }

            if (user == null) {
                error = true;
            }
        }

        session.setAttribute("error", error);
        session.setAttribute("user", user);

        response.sendRedirect("signin.jsp");
    }
}
