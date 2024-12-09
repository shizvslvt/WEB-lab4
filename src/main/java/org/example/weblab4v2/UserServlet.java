package org.example.weblab4v2;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.weblab4v2.controllers.UserController;
import org.example.weblab4v2.classes.User;

import java.io.IOException;
import java.util.List;

@WebServlet("/users")
public class UserServlet extends HttpServlet {
    private UserController userController;

    @Override
    public void init() {
        userController = new UserController();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("a");
        String idParam = request.getParameter("id");


        if (idParam != null) {
            int id = Integer.parseInt(idParam);

            switch (action) {
                case "delete":
                    userController.delete(id);
                    response.sendRedirect("users");
                    break;

                case "update":
                    User user = userController.getUserById(id);
                    request.setAttribute("user", user);
                    request.getRequestDispatcher("/update-user.jsp").forward(request, response);
                    break;
            }
        } else {
            List<User> users = userController.read();
            request.setAttribute("users", users);
            request.getRequestDispatcher("/users.jsp").forward(request, response);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("a");

        switch (action) {
            case "add":
                String name = request.getParameter("name");
                String email = request.getParameter("email");
                userController.create(name, email);
                response.sendRedirect("users");
                break;

            case "update":
                int id = Integer.parseInt(request.getParameter("id"));
                String updatedName = request.getParameter("name");
                String updatedEmail = request.getParameter("mail");
                userController.update(new User(id, updatedName, updatedEmail));
                response.sendRedirect("users");
                break;

            default:
                List<User> userList = userController.read();
                request.setAttribute("users", userList);
                response.sendRedirect("users");
                break;
        }
    }

    @Override
    public void destroy() {
    }
}
