package com.example.gebruiker.tafelsoefenen;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CalculateActivity extends AppCompatActivity {

    // TODO: magic numbers weghalen
    // TODO: meer functies maken (nu alles in onCreate)

    DatabaseHelper db;

    Intent intent;

    ArrayList<Integer> previouslyEarned = new ArrayList<>();

    ArrayList<String> multiplications = new ArrayList<>();
    ArrayList<Integer> answers = new ArrayList<>();
    ArrayList<Integer> levels = new ArrayList<>();
    ArrayList<Integer> ids = new ArrayList<>();

    ArrayList<Exercise> resultExercises = new ArrayList<>();
    ArrayList<Integer> givenAnswers = new ArrayList<>();

    ProgressBar progressBar;

    long start_time;

    private int amount;
    private int counter = 0;
    private int levelBoolean;

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

        // TODO: dit netter in een loopje
        exercisesList.remove(0);
        exercisesList.remove(0);
        exercisesList.remove(0);


        // get database and select necessary columns with it's data
        db = DatabaseHelper.getInstance(getApplicationContext());
        Cursor cursor = db.selectExercises(exercisesList);
        ArrayList<Exercise> exercises = new ArrayList<>();
        try {
            while (cursor.moveToNext()) {
                String multiplication = cursor.getString(cursor.getColumnIndex("multiplication"));
                int answer = cursor.getInt(2);
                int level = cursor.getInt(4);
                int id = cursor.getInt(0);

                // if user comes from the practice activity, take level in consideration
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

            Collections.shuffle(exercises);

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

        } finally {
            cursor.close();
        }

        // show the first question
        TextView questionField = findViewById(R.id.questionField);
        questionField.setText(multiplications.get(counter));

        // set the start time
        start_time = System.currentTimeMillis();

        // set max progressbar
        progressBar = findViewById(R.id.progressBar);
        progressBar.setMax(amount);


        //TODO dit in een losse functie
        EditText editText = (EditText) findViewById(R.id.answerField);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    String answerCheck = ((TextView) findViewById(R.id.answerField)).getText().toString();

                    // if answer is given proceed otherwise clicking the button won't work
                    if (!answerCheck.equals("")) {
                        long end_time = System.currentTimeMillis();

                        // determine the correctness of the answer
                        int answer = Integer.parseInt(answerCheck);
                        int correctness;
                        if (answer == answers.get(counter)) {
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
                        resultExercises.add(new Exercise(multiplications.get(counter),
                                answers.get(counter), correctness));

                        // add answer to the given answers list
                        givenAnswers.add(answer);

                        // update level in database
                        if (levelBoolean == 1) {

                            // determine new level
                            int oldLevel = levels.get(counter);
                            int levelUpdate = correctness - 2;
                            int newLevel = oldLevel + levelUpdate;
                            if (oldLevel == 0) {
                                newLevel = correctness;
                            } else if (newLevel < 1) {
                                newLevel = 1;
                            } else if (newLevel > 4) {
                                newLevel = 4;
                            }

                            // update level
                            db.updateLevel(ids.get(counter), newLevel);
                        }

                        // check if all exercises are made
                        if (counter < amount) {

                            // show the next question and increment progressbar
                            counter++;
                            progressBar.setProgress(counter);
                            TextView questionField = findViewById(R.id.questionField);
                            questionField.setText(multiplications.get(counter));

                            // set new starttime
                            start_time = System.currentTimeMillis();

                            // clear answerfield
                            TextView answerField = findViewById(R.id.answerField);
                            answerField.setText("");

                        } else {

                            // TODO: dit uiteiendleijk in een losse functie maken

                            TrophyDatabaseHelper dbTrophy = TrophyDatabaseHelper.getInstance(getApplicationContext());
                            DatabaseHelper dbExercises = DatabaseHelper.getInstance(getApplicationContext());
                            dbTrophy.updateTrophies(dbExercises);
                            HashMap<Boolean, ArrayList<Integer>> earnedMap = dbTrophy
                                    .checkTrophies(previouslyEarned);

                            Log.d("test", "onEditorAction: " + earnedMap);
                            Log.d("test", "onEdiotrAction: size " + earnedMap.get(true).size());

                            ArrayList<Integer> trophiesEarned = new ArrayList<>();
                            for (int i = 0; i < earnedMap.get(true).size(); i++) {
                                Log.d("test", "onEditorAction: kom ik in de for");
                                trophiesEarned.add(earnedMap.get(true).get(i));
                            }

                            Log.d("test", "onEditorAction: trophiesEarned " + trophiesEarned);

                            if (trophiesEarned.size() != 0) {
                                intent = new Intent(CalculateActivity.this, TrophyEarnedActivity.class);
                                intent.putExtra("trophiesEarned", trophiesEarned);
                            } else {
                                // go to result list activity and give the results of the exercises to that activity
                                intent = new Intent(CalculateActivity.this, ResultListActivity.class);
                            }

                            intent.putExtra("resultExercises", resultExercises);
                            intent.putExtra("givenAnswers", givenAnswers);
                            startActivity(intent);
                        }
                    }

                    handled = true;
                }
                return handled;
            }
        });
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
