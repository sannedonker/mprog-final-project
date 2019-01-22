package com.example.gebruiker.tafelsoefenen;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Size;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class TrophySpecificActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trophy_specific);

        Intent intent = getIntent();
        Trophy trophy = (Trophy) intent.getSerializableExtra("trophy");

        TextView name = findViewById(R.id.name);
        name.setText(trophy.getName());

        ImageView image = findViewById(R.id.trophy_image);
        if (trophy.getEarned() == 1) {
            image.setImageResource(getResources().getIdentifier("trophy_1", "drawable", getPackageName()));
            TextView description = findViewById(R.id.description);
            description.setText(trophy.getDescription());
        }
    }
}
