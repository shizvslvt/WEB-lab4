package org.example.weblab4v2.controllers;

import org.example.weblab4v2.classes.Query;

import java.util.List;

public class QueryController {
    private DatabaseController databaseController = new DatabaseController();

    public Query executeQuery(String queryString) {
        List<String[]> results = databaseController.executeQuery(queryString);
        return new Query(queryString, results);
    }
}
