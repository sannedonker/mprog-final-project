package com.example.gebruiker.tafelsoefenen.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.gebruiker.tafelsoefenen.R;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // go to BeginActivity
    public void beginClick(View view) {
        Intent intent = new Intent(MainActivity.this, BeginActivity.class);
        startActivity(intent);
    }

    // go to CalculateActivity
    public void calculateClick(View view) {
        ArrayList<Integer> exercisesList = new ArrayList<>();
        exercisesList.addAll(Arrays.asList(20, 1, 1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

        Intent intent = new Intent(MainActivity.this, CalculateActivity.class);
        intent.putExtra("exercisesList", exercisesList);
        startActivity(intent);
    }

    // go to SpecificActivity
    public void specificClick(View view) {
        Intent intent = new Intent(MainActivity.this, SpecificActivity.class);
        startActivity(intent);    }

    // go to ResultActivity
    public void resultClick(View view) {
        Intent intent = new Intent(MainActivity.this, ResultActivity.class);
        startActivity(intent);    }

    // go to ResetActivity
    public void resetClick(View view) {
        Intent intent = new Intent(MainActivity.this, ResetActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);    }

    // go to InfoActivity
    public void infoClick(View view) {
        Intent intent = new Intent(MainActivity.this, InfoActivity.class);
        startActivity(intent);    }

    // go to TrophyActivity
    public void trophyClick(View view) {
        Intent intent = new Intent(MainActivity.this, TrophyActivity.class);
        startActivity(intent);    }
}
