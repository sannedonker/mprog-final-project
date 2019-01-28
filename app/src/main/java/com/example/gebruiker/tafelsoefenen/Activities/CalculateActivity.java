package com.example.gebruiker.tafelsoefenen.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.gebruiker.tafelsoefenen.Databases.DatabaseHelper;
import com.example.gebruiker.tafelsoefenen.Classes.Exercise;
import com.example.gebruiker.tafelsoefenen.R;
import com.example.gebruiker.tafelsoefenen.Databases.TrophyDatabaseHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class CalculateActivity extends AppCompatActivity {

    // TODO: scope variabelen checken
    // TODO: meer functies maken (nu alles in onCreate)

    DatabaseHelper db;

    Intent intent;

    ArrayList<Integer> previouslyEarned = new ArrayList<>();

    ArrayList<Exercise> exercises = new ArrayList<>();

    ArrayList<String> multiplications = new ArrayList<>();
    ArrayList<Integer> answers = new ArrayList<>();
    ArrayList<Integer> levels = new ArrayList<>();
    ArrayList<Integer> ids = new ArrayList<>();

    ArrayList<Exercise> resultExercises = new ArrayList<>();
    ArrayList<String> givenAnswers = new ArrayList<>();

    ProgressBar progressBar;

    long startTime;
    long endTime;

    private int amount;
    private int counter = 0;
    private int levelBoolean;

    // TODO: waarshcijnlijk gaat dit in een kleinere scope kunnen
    int greenLevel = 1;
    int yellowLevel = 2;
    int orangeLevel = 3;
    int redLevel = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);

        // get info of which trophies are already earned
        TrophyDatabaseHelper dbTrophy = TrophyDatabaseHelper.getInstance(getApplicationContext());
        previouslyEarned = dbTrophy.selectEarned();

        // get info of the exercises that need to be practiced
        intent = getIntent();
        ArrayList<Integer> exercisesList = intent.getIntegerArrayListExtra("exercisesList");

        // set necessary info in variables
        amount = exercisesList.get(0) - 1;
        levelBoolean = exercisesList.get(1);
        int practiceBoolean = exercisesList.get(2);

        // remove amount of exercises and booleans
        exercisesList.remove(0);
        exercisesList.remove(0);
        exercisesList.remove(0);

        // get exercises from database
        db = DatabaseHelper.getInstance(getApplicationContext());
        Cursor cursor = db.selectExercises(exercisesList);
        try {
            while (cursor.moveToNext()) {
                getExercise(cursor, practiceBoolean);
            }
        } finally {
            cursor.close();
        }

        // select the exercises that will be shown
        selectExercises();

        // show the first question
        TextView questionField = findViewById(R.id.questionField);
        questionField.setText(multiplications.get(counter));

        // set the start time
        startTime = System.currentTimeMillis();

        // set max progressbar
        progressBar = findViewById(R.id.progressBar);
        progressBar.setMax(amount);

        // submit given answer to db
        submitAnswer();

        // TODO: hiernaar refereren: https://developer.android.com/training/keyboard-input/style#java
    }


    // get exercises from cursor
    public void getExercise(Cursor cursor, int practiceBoolean) {

        // get info from cursor
        String multiplication = cursor.getString(cursor.getColumnIndex("multiplication"));
        int answer = cursor.getInt(cursor.getColumnIndex("answer"));
        int level = cursor.getInt(cursor.getColumnIndex("level"));
        int id = cursor.getInt(cursor.getColumnIndex("_id"));

        // take level in consideration if user comes from calculateClick
        if (practiceBoolean == 1) {

            // set chance per level
            int levelChance;
            if (level == 0) {
                levelChance = 1;
            } else {
                levelChance = level;
            }

            // set exercise in list, amount according on levelChance
            for (int i = 0; i < levelChance; i++) {
                exercises.add(new Exercise(multiplication, answer, level, id));
            }

        } else {
            exercises.add(new Exercise(multiplication, answer, level, id));
        }

    }


    // select exercises from exercises list
    public void selectExercises() {

        // shuffle the exercises
        Collections.shuffle(exercises);

        // select as many exercises as needed
        for (int i = 0, j = 0; i < amount + 1; i++, j++) {
            Exercise exercise = exercises.get(j);

            // make sure that there are no duplicates in the practice list
            if (multiplications.contains(exercise.getMultiplication())) {
                i--;
            } else {
                multiplications.add(exercise.getMultiplication());
                answers.add(exercise.getAnswer());
                levels.add(exercise.getLevel());
                ids.add(exercise.getId());
            }
        }
    }


    // checks if answer is given and submits given answer
    public void submitAnswer() {

        // TODO VERWIJZEN NAAR DE LINK!!!! + EVT EXTRA COMMENT OVER DE HELE LISTENER??
        EditText editText = (EditText) findViewById(R.id.answerField);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    String answerString = ((TextView) findViewById(R.id.answerField)).getText().toString();

                    // if answer is given proceed
                    if (!answerString.equals("")) {
                        endTime = System.currentTimeMillis();

                        // determine the correctness of the answer
                        int answer = Integer.parseInt(answerString);
                        int correctness = determineCorrectness(answer);

                        // add exercise to result list
                        resultExercises.add(new Exercise(multiplications.get(counter),
                                answers.get(counter), correctness));

                        // add answer to the given answers list
                        givenAnswers.add(answerString);

                        // update level in database if needed
                        if (levelBoolean == 1) {
                            updateLevel(correctness);
                        }

                        // check if all exercises are made
                        if (counter < amount) {
                            toNextQuestion();
                        } else {
                            toNextActivity();
                        }
                    }

                    handled = true;
                }
                return handled;
            }
        });
    }

    // determine the correctness of the answer
    public int determineCorrectness(int answer) {

        // check if answer is correct
        int correctness;
        if (answer == answers.get(counter)) {

            // determine level on answerTime
            long answerTime = endTime - startTime;
            int greenTime = 3000;
            int yellowTime = 5000;
            if (answerTime < greenTime) {
                correctness = greenLevel;
            } else if (answerTime < yellowTime) {
                correctness = yellowLevel;
            } else {
                correctness = orangeLevel;
            }

        } else {
            correctness = redLevel;
        }

        return correctness;
    }


    // show the user the next question
    public void toNextQuestion() {

        // increment progressbar
        counter++;
        progressBar.setProgress(counter);

        // show the next question
        TextView questionField = findViewById(R.id.questionField);
        questionField.setText(multiplications.get(counter));

        // set new startTime
        startTime = System.currentTimeMillis();

        // clear answerField
        TextView answerField = findViewById(R.id.answerField);
        answerField.setText("");
    }


    // send user to the next activity (either TrophyEarnedActivity or ResultListActivity)
    public void toNextActivity() {

        // select trophies that user has after calculating and before calculating
        TrophyDatabaseHelper dbTrophy = TrophyDatabaseHelper.getInstance(getApplicationContext());
        DatabaseHelper dbExercises = DatabaseHelper.getInstance(getApplicationContext());
        dbTrophy.updateTrophies(dbExercises);
        HashMap<Boolean, ArrayList<Integer>> earnedMap = dbTrophy.checkTrophies(previouslyEarned);

        // check if user earned new trophies
        ArrayList<Integer> trophiesEarned = new ArrayList<>();
        for (int i = 0; i < earnedMap.get(true).size(); i++) {
            trophiesEarned.add(earnedMap.get(true).get(i));
        }

        // if user earned new trophies send user to TrophyEarnedActivity, else to ResultListActivity
        if (trophiesEarned.size() != 0) {
            intent = new Intent(CalculateActivity.this, TrophyEarnedActivity.class);
            intent.putExtra("trophiesEarned", trophiesEarned);
        } else {
            intent = new Intent(CalculateActivity.this, ResultListActivity.class);
        }

        intent.putExtra("resultExercises", resultExercises);
        intent.putExtra("givenAnswers", givenAnswers);
        startActivity(intent);
    }


    // update level in database
    public void updateLevel(int correctness) {

        // determine new level
        int oldLevel = levels.get(counter);
        int levelFactor = 2;
        int levelUpdate = correctness - levelFactor;
        int newLevel = oldLevel + levelUpdate;
        if (oldLevel == 0) {
            newLevel = correctness;
        } else if (newLevel < greenLevel) {
            newLevel = greenLevel;
        } else if (newLevel > redLevel) {
            newLevel = redLevel;
        }

        // update level in database
        db.updateLevel(ids.get(counter), newLevel);
    }


    // makes sure that when pressed back the user goes to the MainActivity screen
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(CalculateActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
