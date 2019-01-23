package com.example.gebruiker.tafelsoefenen;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Size;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.jinatonic.confetti.CommonConfetti;
import com.github.jinatonic.confetti.ConfettiManager;

public class TrophySpecificActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trophy_specific);

        // get trophy that is clicked on
        Intent intent = getIntent();
        Trophy trophy = (Trophy) intent.getSerializableExtra("trophy");

        // set name of trophy
        TextView name = findViewById(R.id.name);
        name.setText(trophy.getName());

        // if trophy is earned show trophy image and description
        ImageButton image = findViewById(R.id.trophy_image);
        if (trophy.getEarned() == 1) {

            // TODO als dit werkt verplaatsen naar trophyEarnedActivity



            image.setImageResource(getResources().getIdentifier("trophy_1", "drawable", getPackageName()));
            TextView description = findViewById(R.id.description);
            description.setText(trophy.getDescription());
        } else {
            image.setClickable(false);
        }
    }

    public void makeConfetti(View view) {
        final Resources res = getResources();
        int green, yellow, orange, red;
        green = res.getColor(R.color.green);
        yellow = res.getColor(R.color.yellow);
        orange = res.getColor(R.color.orange);
        red = res.getColor(R.color.red);
        int[] colors = new int[] { green, yellow, orange, red };

        ViewGroup container = findViewById(R.id.container);
        CommonConfetti.rainingConfetti(container, colors)
                .stream(5000);
    }
}
