package com.ourhouse.controller;

import com.ourhouse.entity.Bill;
import com.ourhouse.entity.Chore;
import com.ourhouse.entity.User;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(
        name = "displayEditPageServlet",
        urlPatterns = { "/displayEditPageServlet" }
)

/**
 * This servlet class is for redirecting to the edit pages
 *
 * @author Navy Jones
 */
public class DisplayEditPageServlet extends HttpServlet {
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
        if (request.getParameter("memberToEdit") != null) {
            int memberId = Integer.parseInt(request.getParameter("memberToEdit"));
            User member = null;
            for (User mem : user.getHousehold().getMembers()) {
                if (mem.getId() == memberId) {
                    member = mem;
                }
            }

            session.setAttribute("memberToEdit", member);

            response.sendRedirect("editMember.jsp");
        } else if (request.getParameter("choreToEdit") != null) {
            int choreId = Integer.parseInt(request.getParameter("choreToEdit"));
            Chore chore = null;
            for (Chore testChore : user.getHousehold().getChores()) {
                if (testChore.getId() == choreId) {
                    chore = testChore;
                }
            }

            session.setAttribute("choreToEdit", chore);

            response.sendRedirect("editChore.jsp");
        } else if (request.getParameter("billToEdit") != null) {
            int billId = Integer.parseInt(request.getParameter("billToEdit"));
            Bill bill = null;
            for (Bill testBill : user.getHousehold().getBills()) {
                if (testBill.getId() == billId) {
                    bill = testBill;
                }
            }

            session.setAttribute("billToEdit", bill);

            response.sendRedirect("editBill.jsp");
        } else {
            response.sendRedirect("index.jsp");
        }
    }
}

