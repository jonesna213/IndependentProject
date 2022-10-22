package com.ourhouse.chores;

import com.ourhouse.entity.Chore;
import com.ourhouse.persistence.GenericDao;
import java.io.*;
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
        GenericDao<Chore> choreDao = new GenericDao<>(Chore.class);
        String searchTerm = request.getParameter("searchTerm");
        String searchType = request.getParameter("searchType");

        /*Either create another class to do the search stuff
        * OR
        * Do everything in here for searching
        *
        * Im thinking more towards doing it in its own class then returning either null or a list of
        * the search results.
        * */


        response.sendRedirect("chores.jsp");
    }
}

