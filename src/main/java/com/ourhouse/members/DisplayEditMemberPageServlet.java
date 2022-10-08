package com.ourhouse.members;

import com.ourhouse.entity.User;
import com.ourhouse.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(
        name = "displayEditMemberPageServlet",
        urlPatterns = { "/displayEditMemberPageServlet" }
)

/**
 * This servlet class is for displaying the edit member page (needed to id the correct member to edit)
 */
public class DisplayEditMemberPageServlet extends HttpServlet {
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

        User user = (User) session.getAttribute("user");
        int memberId = Integer.parseInt(request.getParameter("memberToEdit"));
        User member = null;
        for (User mem : user.getHousehold().getMembers()) {
            if (mem.getId() == memberId) {
                member = mem;
            }
        }

        session.setAttribute("memberToEdit", member);

        response.sendRedirect("editMember.jsp");
    }
}

