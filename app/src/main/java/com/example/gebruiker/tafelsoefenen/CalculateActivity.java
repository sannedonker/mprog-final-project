package com.example.gebruiker.tafelsoefenen;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CalculateActivity extends AppCompatActivity {

    // TODO: progressbar toevoegen
    // TODO: magic numbers weghalen

    DatabaseHelper db;

    ArrayList<String> multiplications = new ArrayList<>();
    ArrayList<Integer> answers = new ArrayList<>();
    ArrayList<Integer> levels = new ArrayList<>();
    ArrayList<Integer> ids = new ArrayList<>();

    ArrayList<String> multiplicationsRandom = new ArrayList<>();
    ArrayList<Integer> answersRandom = new ArrayList<>();
    ArrayList<Integer> levelsRandom = new ArrayList<>();
    ArrayList<Integer> idsRandom = new ArrayList<>();

    ArrayList<Exercise> resultExercises = new ArrayList<>();

    ProgressBar progressBar;

    long start_time;

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

        // set necessary info in variables
        amount = exercisesList.get(0) - 1;
        levelBoolean = exercisesList.get(1);
        exercisesList.remove(0);
        exercisesList.remove(0);

        // get database and select necessary columns with it's data
        db = DatabaseHelper.getInstance(getApplicationContext());
        Cursor cursor = db.selectExercises(exercisesList);
        try {
            while (cursor.moveToNext()) {
                multiplications.add(cursor.getString(cursor.getColumnIndex("multiplication")));
                answers.add(cursor.getInt(cursor.getColumnIndex("answer")));
                levels.add(cursor.getInt(cursor.getColumnIndex("level")));
                ids.add(cursor.getInt(cursor.getColumnIndex("_id")));
            }

            // create list with amount integers
            ArrayList<Integer> integers = new ArrayList<>();
            for (int i = 0; i < multiplications.size(); i++) {
                integers.add(i);
            }

            // shuffle multiplications, answers and levels similarly
            Random rand = new Random();
            for (int i = 0; i < amount + 1; i++) {
                int index = rand.nextInt(integers.size());
                int value = integers.get(index);
                multiplicationsRandom.add(multiplications.get(value));
                answersRandom.add(answers.get(value));
                levelsRandom.add(levels.get(value));
                idsRandom.add(ids.get(value));
                integers.remove(index);
            }

        } finally {
            cursor.close();
        }

        // show the first question
        TextView questionField = findViewById(R.id.questionField);
        questionField.setText(multiplicationsRandom.get(counter));

        // set the start time
        start_time = System.currentTimeMillis();

        // set max progressbar
        progressBar = findViewById(R.id.progressBar);
        progressBar.setMax(amount);

    }

    public void submitClick(View view) {

        String answerCheck = ((TextView) findViewById(R.id.answerField)).getText().toString();

        // if answer is given proceed otherwise clicking the button won't work
        if (!answerCheck.equals("")) {
            long end_time = System.currentTimeMillis();

            // determine the correctnesslevel of the answer
            int answer = Integer.parseInt(answerCheck);
            int correctness;
            if (answer == answersRandom.get(counter)) {
                long answer_time = end_time - start_time;
                if (answer_time < 3000) {
                    correctness = 1;
                } else if (answer_time < 5000) {
                    correctness = 2;
                } else {
                    correctness = 3;
                }
            } else {
                correctness = 4;
            }

            // add exercise to result list
            resultExercises.add(new Exercise(multiplicationsRandom.get(counter),
                                answersRandom.get(counter), correctness));

            // update level in database
            if (levelBoolean == 1) {

                // determine new level
                int oldLevel = levelsRandom.get(counter);
                int levelUpdate = correctness - 2;
                int newLevel = oldLevel + levelUpdate;
                if (oldLevel == 0) {
                    newLevel = correctness;
                } else if (newLevel < 1) {
                    newLevel = 1;
                } else if (newLevel > 4) {
                    newLevel = 4;
                }

                Log.d("test", "submitClick: kom ik hier " + newLevel);

                // TODO geen errors, maar doet nog niets!
                // update level
                db.updateLevel(idsRandom.get(counter), newLevel);
            }

            // check if all exercises are made
            if (counter < amount) {

                // show the next question and increment progressbar
                counter++;
                progressBar.setProgress(counter);
                TextView questionField = findViewById(R.id.questionField);
                questionField.setText(multiplicationsRandom.get(counter));

                // set new starttime
                start_time = System.currentTimeMillis();

                // clear answerfield
                TextView answerField = findViewById(R.id.answerField);
                answerField.setText("");

            } else {

                // go to result list activity and give the results of the exercises to that activity
                int resultActivity = 0;
                Intent intent = new Intent(CalculateActivity.this, ResultListActivity.class);
                intent.putExtra("resultExercises", resultExercises);
                intent.putExtra("boolean", resultActivity);
                startActivity(intent);
            }
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
