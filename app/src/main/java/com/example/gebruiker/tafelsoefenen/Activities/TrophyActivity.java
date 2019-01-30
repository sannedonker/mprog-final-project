package com.example.gebruiker.tafelsoefenen.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.gebruiker.tafelsoefenen.Databases.DatabaseHelper;
import com.example.gebruiker.tafelsoefenen.R;
import com.example.gebruiker.tafelsoefenen.Classes.Trophy;
import com.example.gebruiker.tafelsoefenen.Adapters.TrophyAdapter;
import com.example.gebruiker.tafelsoefenen.Databases.TrophyDatabaseHelper;

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

            // get correct trophy from cursor
            Cursor cursor = (Cursor) parent.getItemAtPosition(position);
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String description = cursor.getString(cursor.getColumnIndex("description"));
            int earned = cursor.getInt(cursor.getColumnIndex("earned"));
            int drawableId = cursor.getInt(cursor.getColumnIndex("drawableId"));
            Trophy trophy = new Trophy(name, description, earned, drawableId);

            // send user and trophy to TrophySpecificActivity
            Intent intent = new Intent(TrophyActivity.this, TrophySpecificActivity.class);
            intent.putExtra("trophy", trophy);
            startActivity(intent);

        }
    }
}
