package com.example.gebruiker.tafelsoefenen;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.gebruiker.tafelsoefenen.Classes.Trophy;
import com.example.gebruiker.tafelsoefenen.Databases.DatabaseHelper;

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
            descriptions.add("Je hebt alle somen van de tafel van " + (i + 1) + " gememoriseerd!");
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

    // select earned column
    public ArrayList<Integer> selectEarned() {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT earned FROM trophies", null);

        ArrayList<Integer> earned = new ArrayList<>();
        try {
            while (cursor.moveToNext()) {
                earned.add(cursor.getInt(0));
            }
        } finally {
            cursor.close();
        }

        return earned;
    }

    // select newly earned trophies
    public ArrayList<Trophy> selectNewTrophies(ArrayList<Integer> trophyIds){

        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor;

        // convert exercisesList to string
        String trophiesEarned;
        if (trophyIds.size() > 1) {
            trophiesEarned = "(";
            for (int i = 0; i < trophyIds.size(); i ++) {
                if (i != trophyIds.size() - 1) {
                    trophiesEarned = trophiesEarned + trophyIds.get(i) + ", ";
                } else {
                    trophiesEarned = trophiesEarned + trophyIds.get(i) + ")";
                }
            }

            cursor = db.rawQuery("SELECT * FROM trophies WHERE _id IN " + trophiesEarned, null);

        } else {
            trophiesEarned = trophyIds.get(0).toString();
            cursor = db.rawQuery("SELECT * FROM trophies WHERE _id = " + trophiesEarned, null);

        }

        Log.d("test", "selectNewTrophies: trophyIds " + trophyIds);
        Log.d("test", "selectNewTrophies: trophiesEarned " + trophiesEarned);
//        Cursor cursor = db.rawQuery("SELECT * FROM trophies WHERE id IN " + trophiesEarned, null);

        Log.d("test", "selectNewTrophies: cursor columns " + cursor.getColumnCount());

        ArrayList<Trophy> newTrophies = new ArrayList<>();
        try {
            while(cursor.moveToNext()) {
                Log.d("test", "selectNewTrophies: zo vaak kom ik hier");
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String description = cursor.getString(cursor.getColumnIndex("description"));
                newTrophies.add(new Trophy(name, description));
            }
        } finally {
            cursor.close();
        }

        return newTrophies;
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

    // reset all earnedTrophies
    public void resetTrophies() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE trophies SET earned = 0");
    }

    // check if new Trophies are earned
    public HashMap<Boolean, ArrayList<Integer>> checkTrophies(ArrayList<Integer> previouslyEarned) {
        ArrayList<Integer> earned = selectEarned();

        // check which trophies are earned during CalculateActivity
        ArrayList<Integer> earnedNew = new ArrayList<>();
        ArrayList<Integer> earnedPreviously = new ArrayList<>();
        for (int i = 0; i < earned.size(); i++) {
            if (earned.get(i) != previouslyEarned.get(i)){
                earnedNew.add(i + 1);
            } else {
                earnedPreviously.add(i + 1);
            }
        }

        HashMap<Boolean, ArrayList<Integer>> earnedMap = new HashMap<>();
        earnedMap.put(true, earnedNew);
        earnedMap.put(false, earnedPreviously);

        return earnedMap;
    }
}
