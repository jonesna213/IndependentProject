package com.ourhouse.bills;

import com.ourhouse.entity.Bill;
import com.ourhouse.entity.User;
import com.ourhouse.persistence.GenericDao;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(
        name = "editBillServlet",
        urlPatterns = { "/editBillServlet" }
)

/**
 * This servlet class is for Editing a bill
 *
 * @author Navy Jones
 */
public class EditBillServlet extends HttpServlet {
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
        GenericDao<Bill> billDao = new GenericDao<>(Bill.class);
        boolean error = false;

        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String dueDate = request.getParameter("dueDate");
        String amount = request.getParameter("amount");
        String frequency = request.getParameter("frequency");

        User user = (User) session.getAttribute("user");
        Bill billToEdit = (Bill) session.getAttribute("billToEdit");
        user.getHousehold().removeBill(billToEdit);

        if (title.equals("") || dueDate.equals("") || frequency.equals("") || amount.equals("")) {
            session.setAttribute("message", "You need to fill out all the fields (description is optional)");
            error = true;
        } else {
            billToEdit.setTitle(title);
            billToEdit.setDescription(description);
            billToEdit.setDueDate(dueDate);
            billToEdit.setAmount(amount);
            billToEdit.setFrequency(frequency);
            billToEdit.setHousehold(user.getHousehold());

            billDao.saveOrUpdate(billToEdit);
            user.getHousehold().addBill(billToEdit);
        }

        session.setAttribute("error", error);

        response.sendRedirect("editBill.jsp");
    }
}

