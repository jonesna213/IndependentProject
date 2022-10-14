package com.ourhouse.members;

import com.ourhouse.entity.User;
import com.ourhouse.persistence.GenericDao;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(
        name = "editMemberServlet",
        urlPatterns = { "/editMemberServlet" }
)

/**
 * This servlet class is for editing or deleting a member
 *
 * @author Navy Jones
 */
public class EditMemberServlet extends HttpServlet {
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
        User memberToEdit = (User) session.getAttribute("memberToEdit");
        User user = (User) session.getAttribute("user");
        boolean error = false;
        String redirectPage = "editMember.jsp";

        //If the user wanted to delete the member
        String choice = request.getParameter("choice");
        if (choice.equals("Remove Member")) {
            userDao.delete(memberToEdit);
            user.getHousehold().removeMember(memberToEdit);
            session.setAttribute("memberDeleted", true);
            redirectPage = "members.jsp";
        } else {
            String firstName = request.getParameter("fname");
            String lastName = request.getParameter("lname");
            String email = request.getParameter("email");
            String username = request.getParameter("uname");
            String perms = request.getParameter("perms");

            if (username.equals("") || firstName.equals("") || lastName.equals("") || perms.equals("")) {
                session.setAttribute("message", "You need to fill out all the fields (email is optional)");
                error = true;
            } else if (userDao.getByPropertyEqual("username", username).size() != 0 &&
                    !username.equals(memberToEdit.getUsername())){
                session.setAttribute("message", "The username you chose is not available, " +
                        "please choose another or leave the same");
                error = true;
            } else {

                memberToEdit.setFirstName(firstName);
                memberToEdit.setLastName(lastName);
                memberToEdit.setUsername(username);
                memberToEdit.setEmail(email);
                memberToEdit.setPermissions(perms);

                userDao.saveOrUpdate(memberToEdit);
            }
        }

        session.setAttribute("error", error);

        response.sendRedirect(redirectPage);
    }
}

