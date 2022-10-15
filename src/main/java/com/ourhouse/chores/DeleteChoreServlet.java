package com.ourhouse.chores;

import com.ourhouse.entity.Chore;
import com.ourhouse.entity.User;
import com.ourhouse.persistence.GenericDao;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(
        name = "deleteChoreServlet",
        urlPatterns = { "/deleteChoreServlet" }
)

/**
 * This servlet class is for deleting a chore
 *
 * @author Navy Jones
 */
public class DeleteChoreServlet extends HttpServlet {
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
        User user = (User) session.getAttribute("user");

        Chore choreToDelete = (Chore) session.getAttribute("choreToDelete");
        session.removeAttribute("choreToDelete");

        if (choice.equals("Delete Chore")) {
            GenericDao<Chore> choreDao = new GenericDao<>(Chore.class);
            choreDao.delete(choreToDelete);
            user.getHousehold().removeChore(choreToDelete);
            session.setAttribute("choreDeleted", true);
        }

        response.sendRedirect("chores.jsp");
    }
}
