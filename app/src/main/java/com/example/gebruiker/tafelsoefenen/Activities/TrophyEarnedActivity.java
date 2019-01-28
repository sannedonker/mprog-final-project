package com.example.gebruiker.tafelsoefenen.Activities;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gebruiker.tafelsoefenen.Classes.Exercise;
import com.example.gebruiker.tafelsoefenen.R;
import com.example.gebruiker.tafelsoefenen.Classes.Trophy;
import com.example.gebruiker.tafelsoefenen.Databases.TrophyDatabaseHelper;
import com.github.jinatonic.confetti.CommonConfetti;

import java.util.ArrayList;

public class TrophyEarnedActivity extends AppCompatActivity {

    ArrayList<Trophy> newTrophies;
    ArrayList<Exercise> resultExercises = new ArrayList<>();
    ArrayList<Integer> givenAnswers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trophy_earned);

        // get resultExercises and givenAnswers from previous activity
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

    // shows user a new trophy or sends it to ResultListActivity
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

    // when imagebutton is clicked a stream of confetti falls down from top of screen
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
        CommonConfetti.rainingConfetti(container, colors).stream(5000);
    }
}
