package com.example.gebruiker.tafelsoefenen;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

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

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10 ; j++) {
                ContentValues values = new ContentValues();

                String multiplication = j + " x " + i;
                int answer = j * i;

                values.put("multiplication", multiplication);
                values.put("answer", answer);
                values.put("multiplicationTable", i);
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
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM exercises WHERE multiplicationTable IN " + exercisesList, null);
        return cursor;
    }

}
