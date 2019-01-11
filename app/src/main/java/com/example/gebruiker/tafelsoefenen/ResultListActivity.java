package com.example.gebruiker.tafelsoefenen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ResultListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_list);

        // get results from calculate activity
        Intent intent = getIntent();
        ArrayList<Exercise> results = (ArrayList<Exercise>) intent.getSerializableExtra("exerciseList");
        // TODO: dit is null :( :(
        Log.d("test", "onCreate: " + results);

        // instantiate the adapter and attach the adapter to the listview
        ResultListAdapter adapter = new ResultListAdapter(this, R.layout.result_item, results);
        ListView lv = findViewById(R.id.listView);
        lv.setAdapter(adapter);
    }
}
