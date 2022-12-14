package com.ourhouse.members;

import com.ourhouse.entity.User;
import com.ourhouse.persistence.GenericDao;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(
        name = "newMemberServlet",
        urlPatterns = { "/newMemberServlet" }
)

/**
 * This servlet class is for adding a new member
 *
 * @author Navy Jones
 */
public class NewMemberServlet extends HttpServlet {
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
        GenericDao<User> userDao = new GenericDao<>(User.class);
        boolean error = false;

        String firstName = request.getParameter("fname");
        String lastName = request.getParameter("lname");
        String email = request.getParameter("email");
        String username = request.getParameter("uname");
        String perms = request.getParameter("perms");

        User user = (User) session.getAttribute("user");
        User newUser = new User();

        if (username.equals("") || firstName.equals("") || lastName.equals("") || perms.equals("")) {
            session.setAttribute("message", "You need to fill out all the fields (email is optional)");
            error = true;
        } else if (userDao.getByPropertyEqual("username", username).size() != 0){
            session.setAttribute("message", "The username you chose is not available, " +
                    "please choose another");
            error = true;
        } else {
            newUser.setFirstName(firstName);
            newUser.setLastName(lastName);
            newUser.setEmail(email);
            newUser.setUsername(username);
            newUser.setPermissions(perms);
            newUser.setHousehold(user.getHousehold());

            if (userDao.insert(newUser) == 0) {
                error = true;
            }

            user.getHousehold().addMember(newUser);
        }

        session.setAttribute("error", error);

        response.sendRedirect("members.jsp");
    }
}

