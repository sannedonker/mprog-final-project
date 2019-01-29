package com.example.gebruiker.tafelsoefenen.Databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.gebruiker.tafelsoefenen.Classes.Trophy;

import java.util.ArrayList;
import java.util.HashMap;

public class TrophyDatabaseHelper extends SQLiteOpenHelper {

    private Context thisContext;
    private static TrophyDatabaseHelper instance;

    private int amountMultiplications = 10;

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


        // TODO: meer plaatjes maken!!!!

        // set values
        ArrayList<String> names = new ArrayList<>();
        ArrayList<String> descriptions = new ArrayList<>();
        for (int i = 0; i < amountMultiplications; i++) {
            names.add("Tafel van " + (i + 1));
            descriptions.add("Je hebt alle somen van de tafel van " + (i + 1) + " gememoriseerd!");
        }
        descriptions.add("Je hebt alle sommen van alle tafels gememoriseerd!");
        names.add("Alle tafels");

        int drawableId = thisContext.getResources().getIdentifier("trophy_1",
                "drawable", thisContext.getPackageName());

        int defaultEarned = 0;

        // add values to database
        for (int i = 0; i < amountMultiplications + 1; i++) {
            ContentValues values = new ContentValues();

            values.put("name", names.get(i));
            values.put("description", descriptions.get(i));
            values.put("drawableId", drawableId);
            values.put("earned", defaultEarned);

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

        // get database
        SQLiteDatabase db = getWritableDatabase();

        // convert exercisesList to string
        String trophiesEarned = DatabaseHelper.convertArrayListIntoString(trophyIds);

        // get cursor from database
        Cursor cursor = db.rawQuery("SELECT * FROM trophies WHERE _id IN " + trophiesEarned, null);

        // add newly earned trophies to list
        ArrayList<Trophy> newTrophies = new ArrayList<>();
        try {
            while(cursor.moveToNext()) {
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

        // get database
        SQLiteDatabase dbTrophies = getWritableDatabase();

        // initialize counter and earnedValue
        int trophyCounter = 0;
        int earnedValue = 1;

        // get map with the keys true and false and values ArrayLists of earned trophies
        HashMap<Integer, ArrayList<Integer>> levelMap = dbExercises.selectLevel();
        for (int i = 0; i < amountMultiplications; i++) {

            Boolean earned = true;
            ArrayList<Integer> levels = levelMap.get(i + 1);

            // set earned to false if a level isn't 1
            for (int j = 0; j < amountMultiplications; j++) {
                if (levels.get(j) != 1) {
                    earned = false;
                    break;
                }
            }

            if (earned){

//                updateTrophy(dbTrophies, earnedValue, i);

                // set new value of earned
                ContentValues values = new ContentValues();
                values.put("earned", earnedValue);

                // update new value in database
                String[] dbId = new String[] {"" + (i + 1)};
                dbTrophies.update("trophies",values, "_id=?", dbId);

                // increment trophy counter
                trophyCounter++;
            }
        }

        // if all multiplication trophies are earned
        if (trophyCounter == amountMultiplications){

//            updateTrophy(dbTrophies, earnedValue, amountMultiplications);

            ContentValues values = new ContentValues();
            values.put("earned", earnedValue);

            String[] dbId = new String[] {"" + (amountMultiplications + 1)};
            dbTrophies.update("trophies", values,"_id=?", dbId);
        }
    }


    // update a specific trophy in db
    public void updateTrophy(SQLiteDatabase db, int multiplication, int earnedValue) {

        // set new value of earned
        ContentValues values = new ContentValues();
        values.put("earned", earnedValue);

        // update new value in database
        String[] dbId = new String[] {"" + (multiplication + 1)};
        db.update("trophies", values,"_id=?", dbId);
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

        // set list of earnedTrophies to HashMap
        HashMap<Boolean, ArrayList<Integer>> earnedMap = new HashMap<>();
        earnedMap.put(true, earnedNew);
        earnedMap.put(false, earnedPreviously);

        return earnedMap;
    }
}
