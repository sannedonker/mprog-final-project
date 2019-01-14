package com.example.gebruiker.tafelsoefenen;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CalculateActivity extends AppCompatActivity {

    //        TODO: progressbar toevoegen
    // TODO: toast of het goed is?
    // TODO: zorgen dat ze input MOETEN geven
    // TODO: nu alleen 2 tafels (andere manier van shuffelen gaan implementeren)

    DatabaseHelper db;

    ArrayList<String> multiplications = new ArrayList<>();
    ArrayList<Integer> answers = new ArrayList<>();
    ArrayList<Integer> levels = new ArrayList<>();

    ArrayList<String> multiplicationsRandom = new ArrayList<>();
    ArrayList<Integer> answersRandom = new ArrayList<>();
    ArrayList<Integer> levelsRandom = new ArrayList<>();

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
            }

            // create list with amount integers
            ArrayList<Integer> integers = new ArrayList<>();
            for (int i = 0; i < amount + 1; i++) {
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

    }

    public void submitClick(View view) {

        String answerCheck = ((TextView) findViewById(R.id.answerField)).getText().toString();

        // if answer is given proceed otherwise clicking the button won't work
        if (!answerCheck.equals("")) {
            long end_time = System.currentTimeMillis();

            ArrayList<Exercise> resultExercises = new ArrayList<>();

            // determine the correctnesslevel of the answer
            int answer = Integer.parseInt(answerCheck);
            int correctness;
            if (answer == answersRandom.get(counter)) {
                long answer_time = end_time - start_time;
                if (answer_time < 3) {
                    correctness = 1;
                } else if (answer_time < 5) {
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
                //TODO: level updaten in database
            }

            // check if all exercises are made
            if (counter < amount) {

                // show the next question
                counter++;
                TextView questionField = findViewById(R.id.questionField);
                questionField.setText(multiplicationsRandom.get(counter));

                // clear answerfield
                TextView answerField = findViewById(R.id.answerField);
                answerField.setText("");

            } else {

                // go to result list activity and give the results of the exercises to that activity
                Intent intent = new Intent(CalculateActivity.this, ResultListActivity.class);
                intent.putExtra("resultExercises", resultExercises);
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
