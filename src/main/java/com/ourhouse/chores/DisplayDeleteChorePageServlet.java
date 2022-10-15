package com.ourhouse.chores;

import com.ourhouse.entity.Chore;
import com.ourhouse.entity.User;
import com.ourhouse.persistence.GenericDao;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(
        name = "displayDeleteChorePageServlet",
        urlPatterns = { "/displayDeleteChorePageServlet" }
)

/**
 * This servlet class is for displaying delete chore page (needed to id the correct member to edit)
 *
 * @author Navy Jones
 */
public class DisplayDeleteChorePageServlet extends HttpServlet {
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
        int choreId = Integer.parseInt(request.getParameter("choreToDelete"));
        Chore chore = null;
        for (Chore testChore : user.getHousehold().getChores()) {
            if (testChore.getId() == choreId) {
                chore = testChore;
            }
        }

        session.setAttribute("choreToDelete", chore);

        response.sendRedirect("deleteChore.jsp");
    }
}

