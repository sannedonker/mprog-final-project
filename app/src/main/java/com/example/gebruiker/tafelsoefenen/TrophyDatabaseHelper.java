package com.example.gebruiker.tafelsoefenen;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class TrophyDatabaseHelper extends SQLiteOpenHelper {

    private Context thisContext;
    private static TrophyDatabaseHelper instance;

    // get correct instance of database
    public static TrophyDatabaseHelper getInstance(Context context) {
        if (instance != null) {
            return instance;
        }
        else {
            instance = new TrophyDatabaseHelper(context, "trophies" ,null, 1);
            return instance;
        }
    }

    // create database with multiplication tables (once)
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table trophies ( _id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "name String, description String, drawableId INTEGER, earned INTEGER)");

        // set values
        String names[] = {"Tafel van 1", "Tafel van 2", "Tafel van 3", "Tafel van 4", "Tafel van 5",
                          "Tafel van 6", "Tafel van 7", "Tafel van 8", "Tafel van 9", "Tafel van 10",
                          "Alle tafels"};

        // TODO: later verschillende drawable ids OF allemaal zelfde plaatje maar dan ander plaatje

        int drawableId = thisContext.getResources().getIdentifier("trophy_1",
                "drawable", thisContext.getPackageName());
        ArrayList<String> descriptions = new ArrayList<>();
        for (int i = 0; i < names.length - 1; i++) {
            descriptions.add("Je hebt alle somen van de tafel van " + i + " gememoriseerd!");
        }
        descriptions.add("Je hebt alle sommen van alle tafels gememoriseerd!");

        // add values to database
        for (int i = 0; i < names.length; i++) {
            ContentValues values = new ContentValues();

            values.put("name", names[i]);
            values.put("description", descriptions.get(i));
            values.put("drawableId", drawableId);
            values.put("earned", 0);

            db.insert("trophies", null, values);
        }
    }

    // update database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + "exercises");
        onCreate(db);
    }

    // constructor
    public TrophyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        thisContext = context;
    }

    // select all info from database
    public Cursor selectAll() {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM trophies", null);
        return cursor;
    }

    // update trophies
    public void updateTrophies (DatabaseHelper dbExercises) {

        SQLiteDatabase dbTrophies = getWritableDatabase();
        int counter = 0;

        HashMap<Integer, ArrayList<Integer>> levelMap = dbExercises.selectLevel();
        // TODO: magic number 10 weghalen
        for (int i = 0; i < 10; i++) {
            Boolean earned = true;
            ArrayList<Integer> levels = levelMap.get(i + 1);
            for (int j = 0; j < 10; j++) {
                if (levels.get(j) != 1) {
                    earned = false;
                    break;
                }
            }

            if (earned){

                // get new value of level
                ContentValues values = new ContentValues();
                values.put("earned", 1);

                // update database
                String[] dbId = new String[] {"" + (i + 1)};
                dbTrophies.update("trophies",values, "_id=?", dbId);

                // increment trophy counter
                counter++;
            }
        }

        // TODO: waarom is het 11????
        // if all multiplication trophies are earned
        if (counter == 10){
            ContentValues values = new ContentValues();
            values.put("earned", 1);

            String[] dbId = new String[] {"" + 11};
            dbTrophies.update("trophies", values,"_id=?", dbId);
        }
    }
}
