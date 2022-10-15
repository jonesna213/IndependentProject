package com.ourhouse.chores;

import com.ourhouse.entity.Chore;
import com.ourhouse.entity.User;
import com.ourhouse.persistence.GenericDao;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(
        name = "addChoreServlet",
        urlPatterns = { "/addChoreServlet" }
)

/**
 * This servlet class is for adding a chore
 *
 * @author Navy Jones
 */
public class AddChoreServlet extends HttpServlet {
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
        Chore newChore = new Chore();

        if (name.equals("") || completeBy.equals("") || frequency.equals("")) {
            session.setAttribute("message", "You need to fill out all the fields (description is optional)");
            error = true;
        } else {
            newChore.setName(name);
            newChore.setDescription(description);
            newChore.setCompleteBy(completeBy);
            newChore.setFrequency(frequency);
            newChore.setHousehold(user.getHousehold());

            if (choreDao.insert(newChore) == 0) {
                error = true;
            }

            user.getHousehold().addChore(newChore);
        }

        session.setAttribute("error", error);

        response.sendRedirect("chores.jsp");
    }
}

