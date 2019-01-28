package com.example.gebruiker.tafelsoefenen.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.gebruiker.tafelsoefenen.Classes.Exercise;
import com.example.gebruiker.tafelsoefenen.R;
import com.example.gebruiker.tafelsoefenen.Adapters.ResultListSpecificAdapter;

import java.util.ArrayList;

public class ResultListSpecificActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_list_specific);

        // get results from result activity
        Intent intent = getIntent();
        ArrayList<Exercise> results = (ArrayList<Exercise>) intent.getSerializableExtra(
                "resultExercises");

        // instantiate the adapter and attach the adapter to the listview
        ResultListSpecificAdapter adapter = new ResultListSpecificAdapter(this,
                R.layout.result_item, results);
        ListView lv = findViewById(R.id.lv_result_specific);
        lv.setAdapter(adapter);
    }

}
