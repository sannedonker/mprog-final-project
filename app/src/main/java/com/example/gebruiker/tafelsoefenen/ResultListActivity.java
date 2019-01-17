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
//        Boolean resultActivity = intent.getBooleanExtra("boolean");
        resultActivity = intent.getIntExtra("boolean", 0);

        // instantiate the adapter and attach the adapter to the listview
        ResultListAdapter adapter = new ResultListAdapter(this, R.layout.result_item, results);
        ListView lv = findViewById(R.id.listView);
        lv.setAdapter(adapter);
    }

    // TODO zorgen dat als je vanuit resultActivity komt dat die dan terug gaat naar daar
    // makes sure that when pressed back the user goes to the MainActivity screen
    @Override
    public void onBackPressed()
    {
        if (resultActivity == 1) {
            intent = new Intent(ResultListActivity.this, ResultActivity.class);
        } else {
            intent = new Intent(ResultListActivity.this, MainActivity.class);
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
