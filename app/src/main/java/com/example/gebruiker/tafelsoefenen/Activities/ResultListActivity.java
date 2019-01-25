package com.example.gebruiker.tafelsoefenen.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.gebruiker.tafelsoefenen.Exercise;
import com.example.gebruiker.tafelsoefenen.R;
import com.example.gebruiker.tafelsoefenen.ResultListAdapter;

import java.util.ArrayList;

public class ResultListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_list);

        // get results from calculate activity
        Intent intent = getIntent();
        ArrayList<Exercise> results = (ArrayList<Exercise>) intent.getSerializableExtra("resultExercises");
        ArrayList<Integer> givenAnswers = intent.getIntegerArrayListExtra("givenAnswers");

        // TODO nieuwe adapter maken! :(

        // instantiate the adapter and attach the adapter to the listview
        ResultListAdapter adapter = new ResultListAdapter(this, R.layout.result_item, results, givenAnswers);
        ListView lv = findViewById(R.id.listView);
        lv.setAdapter(adapter);
    }

    // makes sure that when pressed back the user goes to the correct screen
    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(ResultListActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
