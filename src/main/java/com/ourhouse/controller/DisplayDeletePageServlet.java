package com.ourhouse.controller;

import com.ourhouse.entity.Bill;
import com.ourhouse.entity.Chore;
import com.ourhouse.entity.User;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(
        name = "displayDeletePageServlet",
        urlPatterns = { "/displayDeletePageServlet" }
)

/**
 * This servlet class is for displaying delete page
 *
 * @author Navy Jones
 */
public class DisplayDeletePageServlet extends HttpServlet {
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
        if (request.getParameter("choreToDelete") != null) {
            int choreId = Integer.parseInt(request.getParameter("choreToDelete"));
            Chore chore = null;
            for (Chore testChore : user.getHousehold().getChores()) {
                if (testChore.getId() == choreId) {
                    chore = testChore;
                }
            }

            session.setAttribute("choreToDelete", chore);

            response.sendRedirect("delete.jsp");
        } else if (request.getParameter("billToDelete") != null) {
            int billId = Integer.parseInt(request.getParameter("billToDelete"));
            Bill bill = null;
            for (Bill testBill : user.getHousehold().getBills()) {
                if (testBill.getId() == billId) {
                    bill = testBill;
                }
            }

            session.setAttribute("billToDelete", bill);

            response.sendRedirect("delete.jsp");
        }

    }
}

