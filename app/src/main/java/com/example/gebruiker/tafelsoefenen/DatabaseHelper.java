package com.example.gebruiker.tafelsoefenen;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static DatabaseHelper instance;

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

        // add multiplications in database
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10 ; j++) {
                ContentValues values = new ContentValues();

                int a = i + 1;
                int b = j + 1;
                String multiplication = b + " x " + a;
                int answer = b * a;

                values.put("multiplication", multiplication);
                values.put("answer", answer);
                values.put("multiplicationTable", a);
                values.put("level", 0);

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
        String multiplications = "(";
        for (int i = 0; i < exercisesList.size(); i ++) {
            if (i != exercisesList.size() - 1) {
                multiplications = multiplications + exercisesList.get(i) + ", ";
            } else {
                multiplications = multiplications + exercisesList.get(i) + ")";
            }
        }

        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM exercises WHERE multiplicationTable IN "
                + multiplications, null);

        return cursor;
    }

    // update level on id
    public void updateLevel(int id, int newLevel) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE exercises SET level = " + newLevel + " WHERE _id = " + id);
    }

    // reset all levels
    public void resetLevel() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE exercises SET level = 0");
    }

    // select all levels
    public HashMap selectLevel() {
        SQLiteDatabase db = getWritableDatabase();

        HashMap<Integer, ArrayList<Integer>> levelMap = new HashMap<>();

        for (int i = 0; i < 10; i++) {
            Cursor cursor = db.rawQuery("SELECT level FROM exercises WHERE multiplicationTable = " + (i + 1), null);
            ArrayList<Integer> levels = new ArrayList<>();
            while (cursor.moveToNext()) {
                levels.add(cursor.getColumnIndex("level"));
            }
            levelMap.put(i + 1, levels);
        }
        Log.d("test", "selectLevel: " + levelMap);

        return levelMap;
    }

}
