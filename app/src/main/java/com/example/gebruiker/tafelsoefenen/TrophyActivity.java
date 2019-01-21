package com.example.gebruiker.tafelsoefenen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

public class TrophyActivity extends AppCompatActivity {

    private TrophyDatabaseHelper db;
    private TrophyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trophy);

        // get updated database and adapter
        db = TrophyDatabaseHelper.getInstance(getApplicationContext());
        adapter = new TrophyAdapter(TrophyActivity.this, db.selectAll());

        // set the listview
        GridView gv = findViewById(R.id.gridview);
        gv.setAdapter(adapter);

    }
}
