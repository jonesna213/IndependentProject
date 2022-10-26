package com.ourhouse.chores;

import com.ourhouse.entity.Chore;
import com.ourhouse.persistence.GenericDao;
import com.ourhouse.persistence.Search;

import java.io.*;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(
        name = "searchChoresServet",
        urlPatterns = { "/searchChoresServet" }
)

/**
 * This servlet class is for searching chores
 *
 * @author Navy Jones
 */
public class SearchChoresServlet extends HttpServlet {
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
        Search search = new Search();
        String searchTerm = request.getParameter("searchTerm");
        String searchType = request.getParameter("searchType");

        List<Chore> results = search.searchChores(searchTerm, searchType);

        session.setAttribute("results", results);
        session.setAttribute("search", true);

        response.sendRedirect("chores.jsp");
    }
}

