package org.example.weblab4v2.classes;

import java.util.List;

public class Query {
    private String query;
    private List<String[]> result;

    public Query(String query, List<String[]> result) {
        this.query = query;
        this.result = result;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public List<String[]> getResult() {
        return result;
    }

    public void setResult(List<String[]> result) {
        this.result = result;
    }
}