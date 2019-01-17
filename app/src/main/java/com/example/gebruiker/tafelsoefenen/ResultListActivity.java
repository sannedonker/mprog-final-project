package com.example.gebruiker.tafelsoefenen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ResultListActivity extends AppCompatActivity {

    private int resultActivity;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_list);

        // get results from calculate activity
        intent = getIntent();
        ArrayList<Exercise> results = (ArrayList<Exercise>) intent.getSerializableExtra("resultExercises");
        resultActivity = intent.getIntExtra("boolean", 0);

        // instantiate the adapter and attach the adapter to the listview
        ResultListAdapter adapter = new ResultListAdapter(this, R.layout.result_item, results);
        ListView lv = findViewById(R.id.listView);
        lv.setAdapter(adapter);
    }

    // makes sure that when pressed back the user goes to the correct screen
    @Override
    public void onBackPressed()
    {
        // TODO: zorgen dat je op de zelfde hoogte terug komt in ResultActivity

        // if 1 return to ResultActivity, else return to MainActivity
        if (resultActivity == 1) {
            intent = new Intent(ResultListActivity.this, ResultActivity.class);
        } else {
            intent = new Intent(ResultListActivity.this, MainActivity.class);
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
