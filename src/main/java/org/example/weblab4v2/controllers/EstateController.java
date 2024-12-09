package org.example.weblab4v2.controllers;

import org.example.weblab4v2.classes.Estate;

import java.time.LocalDateTime;
import java.util.List;

public class EstateController {
    DatabaseController databaseController = new DatabaseController();

    public List<Estate> read() {
        return databaseController.estate_read();
    }

    public void create(int sellerId, String title, double cost, LocalDateTime time) {
        databaseController.estate_create(sellerId, title, cost, time);
    }

    public void delete(int id) {
        databaseController.estate_delete(id);
    }

    public Estate getEstateById(int id) {
        return databaseController.getEstateById(id);
    }


    public void update(Estate estate) {
        databaseController.estate_update(estate);
    }
}
