package com.ourhouse.controller;

import com.ourhouse.entity.Bill;
import com.ourhouse.entity.Chore;
import com.ourhouse.entity.User;
import com.ourhouse.persistence.GenericDao;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(
        name = "deleteServlet",
        urlPatterns = { "/deleteServlet" }
)

/**
 * This servlet class is for deleting an item
 *
 * @author Navy Jones
 */
public class DeleteServlet extends HttpServlet {
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
        String redirectURL = "index.jsp";

        switch (choice) {
            case "Delete Chore":
                Chore choreToDelete = (Chore) session.getAttribute("choreToDelete");
                session.removeAttribute("choreToDelete");
                GenericDao<Chore> choreDao = new GenericDao<>(Chore.class);
                choreDao.delete(choreToDelete);
                user.getHousehold().removeChore(choreToDelete);
                session.setAttribute("choreDeleted", true);
                redirectURL = "chores.jsp";
                break;
            case "Dont Delete Chore":
                redirectURL = "chores.jsp";
                break;
            case "Delete Bill":
                Bill billToDelete = (Bill) session.getAttribute("billToDelete");
                session.removeAttribute("billToDelete");
                GenericDao<Bill> billDao = new GenericDao<>(Bill.class);
                billDao.delete(billToDelete);
                user.getHousehold().removeBill(billToDelete);
                session.setAttribute("billDeleted", true);
                redirectURL = "bills.jsp";
                break;
            case "Dont Delete Bill":
                redirectURL = "bills.jsp";
                break;
        }

        response.sendRedirect(redirectURL);
    }
}
