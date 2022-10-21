package com.ourhouse.chores;

import com.ourhouse.entity.Chore;
import com.ourhouse.entity.User;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(
        name = "displayEditChorePageServlet",
        urlPatterns = { "/displayEditChorePageServlet" }
)

/**
 * This servlet class is for displaying the edit chore page
 *
 * @author Navy Jones
 */
public class DisplayEditChorePageServlet extends HttpServlet {
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

        User user = (User) session.getAttribute("user");
        int choreId = Integer.parseInt(request.getParameter("choreToEdit"));
        Chore chore = null;
        for (Chore testChore : user.getHousehold().getChores()) {
            if (testChore.getId() == choreId) {
                chore = testChore;
            }
        }

        session.setAttribute("choreToEdit", chore);

        response.sendRedirect("editChore.jsp");
    }
}

