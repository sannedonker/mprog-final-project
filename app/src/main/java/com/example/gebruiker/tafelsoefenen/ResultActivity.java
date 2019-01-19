package com.example.gebruiker.tafelsoefenen;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.LineChartView;
import lecho.lib.hellocharts.view.PieChartView;

public class ResultActivity extends AppCompatActivity {

    private DatabaseHelper db;
    private PieChartAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // get updated database and adapter
        db = DatabaseHelper.getInstance(getApplicationContext());
        adapter = new PieChartAdapter(db.selectLevel());

        // set the gridview
        GridView gv = findViewById(R.id.result_gridview);
        gv.setAdapter(adapter);
        gv.setOnItemClickListener(new OnItemClickListener());

    }

    // when item clicked go to detailactivity screen
    private class OnItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            // TODO magic number weghalen
            ArrayList<Exercise> resultList = new ArrayList<>();

            ArrayList<Integer> multiplication = new ArrayList<>();
            multiplication.add(position + 1);
            Cursor cursor = db.selectExercises(multiplication);
            try {
                ArrayList<String> multiplications = new ArrayList<>();
                ArrayList<Integer> answers = new ArrayList<>();
                ArrayList<Integer> levels = new ArrayList<>();
                while (cursor.moveToNext()) {
                    multiplications.add(cursor.getString(cursor.getColumnIndex("multiplication")));
                    answers.add(cursor.getInt(cursor.getColumnIndex("answer")));
                    levels.add(cursor.getInt(cursor.getColumnIndex("level")));
                }

                for (int i = 0; i < multiplications.size(); i++) {
                    resultList.add(new Exercise(multiplications.get(i), answers.get(i), levels.get(i)));
                }
            } finally {
                cursor.close();
            }

            Intent intent = new Intent(ResultActivity.this, ResultListSpecificActivity.class);
            intent.putExtra("resultExercises", resultList);
            startActivity(intent);

        }
    }

}
