package com.example.gebruiker.tafelsoefenen.Classes;

import android.os.Parcelable;

import java.io.Serializable;

public class Exercise implements Serializable {

    private String multiplication;
    private int answer, multiplicationTable, level, id;

    public Exercise(String multiplication, int answer, int multiplicationTable, int level, int id) {
        this.multiplication = multiplication;
        this.answer = answer;
        this.multiplicationTable = multiplicationTable;
        this.level = level;
        this.id = id;
    }

    public Exercise(String multiplication, int answer, int level, int id) {
        this.multiplication = multiplication;
        this.answer = answer;
        this.level = level;
        this.id = id;
    }

    public Exercise(String multiplication, int answer, int level) {
        this.multiplication = multiplication;
        this.answer = answer;
        this.level = level;
    }

    public String getMultiplication() {
        return multiplication;
    }

    public int getAnswer() {
        return answer;
    }

    public int getMultiplicationTable() {
        return multiplicationTable;
    }

    public int getLevel() {
        return level;
    }

    public int getId() {
        return id;
    }

    public void setMultiplication(String multiplication) {
        this.multiplication = multiplication;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public void setMultiplicationTable(int multiplicationTable) {
        this.multiplicationTable = multiplicationTable;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
