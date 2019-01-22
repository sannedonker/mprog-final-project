package com.example.gebruiker.tafelsoefenen;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

public class TrophyActivity extends AppCompatActivity {

    private TrophyDatabaseHelper db;
    private TrophyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trophy);

        // get updated database and adapter
        db = TrophyDatabaseHelper.getInstance(getApplicationContext());
        db.updateTrophies(DatabaseHelper.getInstance(getApplicationContext()));
        adapter = new TrophyAdapter(TrophyActivity.this, db.selectAll());

        // set the gridview
        GridView gv = findViewById(R.id.gridview);
        gv.setAdapter(adapter);
        gv.setOnItemClickListener(new TrophyActivity.OnItemClickListener());

    }

    // when item clicked go to TrophySpecificActivity
    private class OnItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            Cursor cursor = (Cursor) parent.getItemAtPosition(position);
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String description = cursor.getString(cursor.getColumnIndex("description"));
            int earned = cursor.getInt(cursor.getColumnIndex("earned"));

            Trophy trophy = new Trophy(name, description, earned);

            Log.d("test", "onItemClick: " + trophy);

            Intent intent = new Intent(TrophyActivity.this, TrophySpecificActivity.class);
            intent.putExtra("trophy", trophy);
            startActivity(intent);

        }
    }
}
