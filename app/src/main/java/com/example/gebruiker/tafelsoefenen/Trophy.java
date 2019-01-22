package com.example.gebruiker.tafelsoefenen;

import java.io.Serializable;

public class Trophy implements Serializable {

    private String name, description;
    private int id, drawableId, earned;


    public Trophy(String name, String description, int id, int drawableId, int earned) {
        this.name = name;
        this.description = description;
        this.id = id;
        this.drawableId = drawableId;
        this.earned = earned;
    }

    public Trophy(String name, String description, int earned) {
        this.name = name;
        this.description = description;
        this.earned = earned;
    }

    public Trophy(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getEarned() {
        return earned;
    }
}
