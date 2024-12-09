package org.example.weblab4v2;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.weblab4v2.controllers.QueryController;
import org.example.weblab4v2.classes.Query;

import java.io.IOException;

@WebServlet("/query")
public class QueryServlet extends HttpServlet {
    private QueryController queryController;

    @Override
    public void init() {
        queryController = new QueryController();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/query.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String queryText = request.getParameter("query");
        Query query = queryController.executeQuery(queryText);

        request.setAttribute("query", query);
        request.getRequestDispatcher("/query.jsp").forward(request, response);
    }
}