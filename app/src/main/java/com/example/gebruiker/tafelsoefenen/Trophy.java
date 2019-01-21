package com.example.gebruiker.tafelsoefenen;

public class Trophy {

    private String name, description;
    private int id, drawableId;
    private Boolean earned;


    public Trophy(String name, String description, int id, int drawableId, Boolean earned) {
        this.name = name;
        this.description = description;
        this.id = id;
        this.drawableId = drawableId;
        this.earned = earned;
    }
}
