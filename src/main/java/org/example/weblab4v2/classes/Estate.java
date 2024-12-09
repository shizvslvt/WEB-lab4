package org.example.weblab4v2.classes;

import java.time.LocalDateTime;

public class Estate {
    private final int id;
    private final int sellerId;
    private final String title;
    private final double cost;
    private final LocalDateTime time;

    public Estate(int id, int sellerId, String title, double cost, LocalDateTime time) {
        this.id = id;
        this.sellerId = sellerId;
        this.title = title;
        this.cost = cost;
        this.time = time;
    }
    public int getId() {
        return id;
    }

    public int getSellerId() {
        return sellerId;
    }

    public String getTitle() {
        return title;
    }

    public double getCost() {
        return cost;
    }

    public LocalDateTime getTime() {
        return time;
    }
}
