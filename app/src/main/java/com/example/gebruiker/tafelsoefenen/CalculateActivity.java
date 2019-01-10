package com.example.gebruiker.tafelsoefenen;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CalculateActivity extends AppCompatActivity {

    //        TODO: progressbar (en dus ook counter ofzo) toevoegen

    DatabaseHelper db;

    ArrayList<String> multiplications = new ArrayList<>();
    ArrayList<Integer> answers = new ArrayList<>();
    ArrayList<Integer> levels = new ArrayList<>();

    private int amount;
    private int counter = 0;
    private int levelBoolean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);

        // get info of the exercises that need to be practiced
        Intent intent = getIntent();
        ArrayList<Integer> exercisesList = intent.getIntegerArrayListExtra("exercisesList");

        amount = exercisesList.get(0);
        levelBoolean = exercisesList.get(1);

        exercisesList.remove(0);
        exercisesList.remove(0);

        Log.d("test", "onCreate: ik kom hier");

        // get database and select necessary columns with it's data
        db = DatabaseHelper.getInstance(getApplicationContext());
        Cursor cursor = db.selectExercises(exercisesList);
        try {
            while (cursor.moveToNext()) {
                multiplications.add(cursor.getString(cursor.getColumnIndex("multiplication")));
                answers.add(cursor.getInt(cursor.getColumnIndex("answer")));
                levels.add(cursor.getInt(cursor.getColumnIndex("level")));
                Log.d("test", "onCreate: hoe vaak kom ik hier");
            }
        } finally {
            cursor.close();
        }

        // show the first question
        TextView questionField = findViewById(R.id.questionField);
        questionField.setText(multiplications.get(counter));

    }

    public void submitClick(View view) {

        ArrayList<Exercise> resultExercises = new ArrayList<>();

        // determine the correctnesslevel of the answer
        int answer = Integer.parseInt(((TextView) findViewById(R.id.answerField)).getText().toString());
        int correctness = 0;
        if (answer == answers.get(counter)) {
            // iets met tijd gaan doen
            // correctness bepalen (groen, geel, oranje)
            // level updaten (als dat moet anders niet)
        } else {
            // correctness bepalen (rood)
            // level updaten (als dat moet anders niet)
        }

        // add exercise to result list
        resultExercises.add(new Exercise(multiplications.get(counter), answers.get(counter), correctness));

        // check if all exercises are made
        if (counter < amount) {

            // show the question
            TextView questionField = findViewById(R.id.questionField);
            questionField.setText(multiplications.get(counter));

        } else {

            // go to result list activity and give the result of the exercises to that activity
            Intent intent = new Intent(CalculateActivity.this, ResultListActivity.class);
            intent.putExtra("resultExercises", resultExercises);
            startActivity(intent);
        }

    }

    // makes sure that when pressed back the user goes to the MainActivity screen
    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(CalculateActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
