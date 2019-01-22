package com.example.gebruiker.tafelsoefenen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class TrophyEarnedActivity extends AppCompatActivity {

    ArrayList<Trophy> newTrophies;
    ArrayList<Exercise> resultExercises = new ArrayList<>();
    ArrayList<Integer> givenAnswers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trophy_earned);

        Log.d("test", "onCreate: kom ik hier???");

        Intent intent = getIntent();
        ArrayList<Integer> trophiesEarned = intent.getIntegerArrayListExtra("trophiesEarned");
        resultExercises = (ArrayList<Exercise>) intent.getSerializableExtra("resultExercises");
        givenAnswers = intent.getIntegerArrayListExtra("givenAnswers");

        // select newly earned trophies
        TrophyDatabaseHelper db = TrophyDatabaseHelper.getInstance(getApplicationContext());
        newTrophies = db.selectNewTrophies(trophiesEarned);

        // set TextViews
        TextView name = findViewById(R.id.name);
        TextView description = findViewById(R.id.description);
        name.setText(newTrophies.get(0).getName());
        description.setText(newTrophies.get(0).getDescription());

        newTrophies.remove(0);
    }

    public void newTrophyClick(View view) {

        // check if more trophies are earned
        if (newTrophies.size() >= 1) {
            Trophy trophy = newTrophies.get(0);

            // set TextViews
            TextView name = findViewById(R.id.name);
            TextView description = findViewById(R.id.description);
            name.setText(trophy.getName());
            description.setText(trophy.getDescription());

            newTrophies.remove(0);
        }

        // go to resultListActivity
        else {
            Intent intent = new Intent(TrophyEarnedActivity.this, ResultListActivity.class);
            intent.putExtra("resultExercises", resultExercises);
            intent.putExtra("givenAnswers", givenAnswers);
            startActivity(intent);
        }

    }
}
