package com.ourhouse.controller;

import com.ourhouse.entity.Bill;
import com.ourhouse.entity.Chore;
import com.ourhouse.entity.User;
import com.ourhouse.persistence.Search;

import java.io.*;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(
        name = "searchServlet",
        urlPatterns = { "/searchServlet" }
)

/**
 * This servlet class is for searching items
 *
 * @author Navy Jones
 */
public class SearchServlet extends HttpServlet {
    /**
     *  Handles HTTP GET requests.
     *
     *@param  request                   the HttpServletRequest object
     *@param  response                   the HttpServletResponse object
     *@exception  ServletException  if there is a Servlet failure
     *@exception  IOException       if there is an IO failure
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String searchTerm = request.getParameter("searchTerm");
        String searchType = request.getParameter("searchType");
        User user = (User) session.getAttribute("user");
        String redirectURL = "index.jsp";

        //If searching for a bill
        if (request.getParameter("returnType").equals("bill")) {
            Search<Bill> search = new Search<>(Bill.class);
            List<Bill> results = search.executeSearch(searchTerm, searchType);
            //Makes sure results were given back then removes all bills not belonging to the user
            if (results != null) {
                results.removeIf(bill -> bill.getHousehold().getId() != user.getHousehold().getId());
                if (results.size() != 0) {
                    session.setAttribute("results", results);
                }
            }
            redirectURL = "bills.jsp";

        //If searching for a chore
        } else if (request.getParameter("returnType").equals("chore")) {
            Search<Chore> search = new Search<>(Chore.class);
            List<Chore> results = search.executeSearch(searchTerm, searchType);
            //Makes sure results were given back then removes all chores not belonging to the user
            if (results != null) {
                results.removeIf(chore -> chore.getHousehold().getId() != user.getHousehold().getId());
                if (results.size() != 0) {
                    session.setAttribute("results", results);
                }
            }
            redirectURL = "chores.jsp";
        }

        session.setAttribute("search", true);

        response.sendRedirect(redirectURL);
    }
}

