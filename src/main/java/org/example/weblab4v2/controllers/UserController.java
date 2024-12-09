package org.example.weblab4v2.controllers;

import org.example.weblab4v2.classes.User;

import java.util.List;

public class UserController {
    DatabaseController databaseController = new DatabaseController();


    public List<User> read() {
        return databaseController.user_read();
    }

    public void create(String name, String email) {
        databaseController.user_create(name, email);
    }

    public void delete(int id) {
        databaseController.user_delete(id);
    }

    public User getUserById(long id) {
        return databaseController.getUserById(id);
    }

    public void update(User user) {
        databaseController.user_update(user);
    }

}
