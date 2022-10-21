package com.ourhouse.chores;

import com.ourhouse.entity.Chore;
import com.ourhouse.entity.User;
import com.ourhouse.persistence.GenericDao;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(
        name = "editChoreServlet",
        urlPatterns = { "/editChoreServlet" }
)

/**
 * This servlet class is for Editing a chore
 *
 * @author Navy Jones
 */
public class EditChoreServlet extends HttpServlet {
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
        GenericDao<Chore> choreDao = new GenericDao<>(Chore.class);
        boolean error = false;

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String completeBy = request.getParameter("completeBy");
        String frequency = request.getParameter("frequency");

        User user = (User) session.getAttribute("user");
        Chore choreToEdit = (Chore) session.getAttribute("choreToEdit");
        user.getHousehold().removeChore(choreToEdit);

        if (name.equals("") || completeBy.equals("") || frequency.equals("")) {
            session.setAttribute("message", "You need to fill out all the fields (description is optional)");
            error = true;
        } else {
            choreToEdit.setName(name);
            choreToEdit.setDescription(description);
            choreToEdit.setCompleteBy(completeBy);
            choreToEdit.setFrequency(frequency);

            choreDao.saveOrUpdate(choreToEdit);
            user.getHousehold().addChore(choreToEdit);
        }

        session.setAttribute("error", error);

        response.sendRedirect("editChore.jsp");
    }
}

