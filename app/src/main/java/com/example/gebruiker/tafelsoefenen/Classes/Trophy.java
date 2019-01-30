package com.example.gebruiker.tafelsoefenen.Classes;

import java.io.Serializable;

public class Trophy implements Serializable {

    private String name, description;
    private int drawableId, earned;

    public Trophy(String name, String description, int earned, int drawableId) {
        this.name = name;
        this.description = description;
        this.earned = earned;
        this.drawableId = drawableId;
    }

    public Trophy(String name, String description, int drawableId) {
        this.name = name;
        this.description = description;
        this.drawableId = drawableId;
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

    public int getDrawableId() {
        return drawableId;
    }
}
