package com.example.gebruiker.tafelsoefenen.Databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static DatabaseHelper instance;
    private int amountMultiplications = 10;

    // get correct instance of database
    public static DatabaseHelper getInstance(Context context) {
        if (instance != null) {
            return instance;
        }
        else {
            instance = new DatabaseHelper(context, "exercises" ,null, 1);
            return instance;
        }
    }

    // create database with multiplication tables (once)
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table exercises ( _id INTEGER PRIMARY KEY AUTOINCREMENT,"
                   + "multiplication String, answer INTEGER, multiplicationTable INTEGER,"
                   + "level INTEGER)");

        int defaultLevel = 0;

        // add multiplications in database
        for (int i = 0; i < amountMultiplications; i++) {
            for (int j = 0; j < amountMultiplications; j++) {
                ContentValues values = new ContentValues();

                // get values
                int a = i + 1;
                int b = j + 1;
                String multiplication = b + " x " + a;
                int answer = b * a;

                // set values
                values.put("multiplication", multiplication);
                values.put("answer", answer);
                values.put("multiplicationTable", a);
                values.put("level", defaultLevel);

                // insert values in database
                db.insert("exercises", null, values);
            }
        }

    }

    // update database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + "exercises");
        onCreate(db);
    }

    // constructor
    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // select needed exercises from database
    public Cursor selectExercises(ArrayList exercisesList) {

        // convert exercisesList to string
        String multiplications = convertArrayListIntoString(exercisesList);

        // get cursor from database
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM exercises WHERE multiplicationTable IN "
                + multiplications, null);

        return cursor;
    }

    // update level on id
    public void updateLevel(int id, int newLevel) {
        SQLiteDatabase db = getWritableDatabase();

        // get new value of level
        ContentValues values = new ContentValues();
        values.put("level", newLevel);

        // update database
        String[] rowId = new String[] {"" + id};
        db.update("exercises",values, "_id=?", rowId);
    }

    // reset all levels
    public void resetLevel() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE exercises SET level = 0");
    }

    // select all levels, put them in a hashmap with key the multiplication
    public HashMap selectLevel() {

        // get database and initialize hashmap
        SQLiteDatabase db = getWritableDatabase();
        HashMap<Integer, ArrayList<Integer>> levelMap = new HashMap<>();

        // select levels per multiplication
        for (int i = 0; i < amountMultiplications; i++) {

            // get cursor
            Cursor cursor = db.rawQuery("SELECT level FROM exercises WHERE multiplicationTable = "
                                        + (i + 1), null);

            // make list with all levels and add list to hashmap
            ArrayList<Integer> levels = new ArrayList<>();
            try {
                while (cursor.moveToNext()) {
                    levels.add(cursor.getInt(0));
                }
                levelMap.put(i + 1, levels);
            } finally {
                cursor.close();
            }
        }

        return levelMap;
    }

    // convert ArrayList into a String
    public static String convertArrayListIntoString (ArrayList inputList){

        // convert exercisesList to string
        String resultString = "(";
        for (int i = 0; i < inputList.size(); i ++) {
            if (i != inputList.size() - 1) {
                resultString = resultString + inputList.get(i) + ", ";
            } else {
                resultString = resultString + inputList.get(i) + ")";
            }
        }

        return resultString;
    }

}
