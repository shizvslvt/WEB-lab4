package org.example.weblab4v2;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.weblab4v2.classes.Estate;
import org.example.weblab4v2.controllers.EstateController;


import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet("/estates")
public class EstateServlet extends HttpServlet {
    private EstateController estateController;

    @Override
    public void init() {
        estateController = new EstateController();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("a");
        String idParam = request.getParameter("id");

        if (idParam != null) {
            int id = Integer.parseInt(idParam);

            switch (action) {
                case "delete":
                    estateController.delete(id);
                    response.sendRedirect("estates");
                    break;

                case "update":
                    Estate estate = estateController.getEstateById(id);
                    request.setAttribute("estate", estate);
                    request.getRequestDispatcher("/update-estate.jsp").forward(request, response);
                    break;

                default:
                    List<Estate> estates = estateController.read();
                    request.setAttribute("estates", estates);
                    request.getRequestDispatcher("/estates.jsp").forward(request, response);
            }
        } else {
            List<Estate> estates = estateController.read();
            request.setAttribute("estates", estates);
            request.getRequestDispatcher("/estates.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("a");

        switch (action) {
            case "add":
                int sellerId = Integer.parseInt(request.getParameter("sellerId"));
                String title = request.getParameter("title");
                double cost = Double.parseDouble(request.getParameter("cost"));
                LocalDateTime time = LocalDateTime.now();
                estateController.create(sellerId, title, cost, time);
                response.sendRedirect("estates");
                break;

            case "update":
                int id = Integer.parseInt(request.getParameter("id"));
                int updatedSellerId = Integer.parseInt(request.getParameter("sellerId"));
                String updatedTitle = request.getParameter("title");
                double updatedCost = Double.parseDouble(request.getParameter("cost"));
                LocalDateTime updatedTime = LocalDateTime.parse(request.getParameter("time"));
                estateController.update(new Estate(id, updatedSellerId, updatedTitle, updatedCost, updatedTime));
                response.sendRedirect("estates");
                break;

            default:
                response.sendRedirect("estates");
                break;
        }
    }

    @Override
    public void destroy() {
    }
}
