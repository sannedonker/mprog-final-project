package com.example.gebruiker.tafelsoefenen.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.gebruiker.tafelsoefenen.Databases.DatabaseHelper;
import com.example.gebruiker.tafelsoefenen.Classes.Exercise;
import com.example.gebruiker.tafelsoefenen.Adapters.PieChartAdapter;
import com.example.gebruiker.tafelsoefenen.R;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    private DatabaseHelper db;
    ArrayList<Exercise> resultList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // get updated database and adapter
        db = DatabaseHelper.getInstance(getApplicationContext());
        PieChartAdapter adapter = new PieChartAdapter(db.selectLevel());

        // set the gridview
        GridView gv = findViewById(R.id.result_gridview);
        gv.setAdapter(adapter);
        gv.setOnItemClickListener(new OnItemClickListener());

    }


    // when piechart clicked go to ResultListActivity
    private class OnItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            // clear resultList
            resultList = new ArrayList<>();

            // select exercises from database for the clicked multiplication
            ArrayList<Integer> multiplication = new ArrayList<>();
            multiplication.add(position + 1);
            Cursor cursor = db.selectExercises(multiplication);
            try {
                getResults(cursor);
            } finally {
                cursor.close();
            }

            // send user to ResultListSpecificActivity
            Intent intent = new Intent(ResultActivity.this, ResultListSpecificActivity.class);
            intent.putExtra("resultExercises", resultList);
            startActivity(intent);

        }
    }


    // get results from cursor
    public void getResults(Cursor cursor) {

        // get info from cursor and add it to lists
        ArrayList<String> multiplications = new ArrayList<>();
        ArrayList<Integer> answers = new ArrayList<>();
        ArrayList<Integer> levels = new ArrayList<>();
        while (cursor.moveToNext()) {
            multiplications.add(cursor.getString(cursor.getColumnIndex("multiplication")));
            answers.add(cursor.getInt(cursor.getColumnIndex("answer")));
            levels.add(cursor.getInt(cursor.getColumnIndex("level")));
        }

        // set exercises in resultList
        for (int i = 0; i < multiplications.size(); i++) {
            resultList.add(new Exercise(multiplications.get(i), answers.get(i), levels.get(i)));
        }
    }

}
