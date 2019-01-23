package com.example.gebruiker.tafelsoefenen;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.jinatonic.confetti.CommonConfetti;
import com.github.jinatonic.confetti.ConfettiView;

import java.util.ArrayList;

public class TrophyEarnedActivity extends AppCompatActivity {

    ArrayList<Trophy> newTrophies;
    ArrayList<Exercise> resultExercises = new ArrayList<>();
    ArrayList<Integer> givenAnswers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trophy_earned);

        Intent intent = getIntent();
        ArrayList<Integer> trophiesEarned = intent.getIntegerArrayListExtra("trophiesEarned");
        resultExercises = (ArrayList<Exercise>) intent.getSerializableExtra("resultExercises");
        givenAnswers = intent.getIntegerArrayListExtra("givenAnswers");

        // select newly earned trophies
        TrophyDatabaseHelper db = TrophyDatabaseHelper.getInstance(getApplicationContext());
        newTrophies = db.selectNewTrophies(trophiesEarned);

        // set name of trophy
        TextView name = findViewById(R.id.name);
        name.setText(newTrophies.get(0).getName());

        newTrophies.remove(0);
    }

    public void newTrophyClick(View view) {

        // check if more trophies are earned
        if (newTrophies.size() >= 1) {
            Trophy trophy = newTrophies.get(0);

            // set name of trophy
            TextView name = findViewById(R.id.name);
            name.setText(trophy.getName());

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

    // TODO deze functie staat ergens anders ook --> kijken hoe ik dit maar 1x ergens hoef te hebben staan
    public void makeConfetti(View view) {

        // set colors
        final Resources res = getResources();
        int green, yellow, orange, red;
        green = res.getColor(R.color.green);
        yellow = res.getColor(R.color.yellow);
        orange = res.getColor(R.color.orange);
        red = res.getColor(R.color.red);
        int[] colors = new int[] { green, yellow, orange, red };

        // make confetti
        ViewGroup container = findViewById(R.id.container);
        CommonConfetti.rainingConfetti(container, colors)
                .stream(5000);
    }
}
