package com.example.gebruiker.tafelsoefenen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // go to begin activity
    public void beginClick(View view) {
        Intent intent = new Intent(MainActivity.this, BeginActivity.class);
        startActivity(intent);
    }

    // go to calculate activity
    public void calculateClick(View view) {
        ArrayList<Integer> exercisesList = new ArrayList<>();
        exercisesList.addAll(Arrays.asList(20, 1, 1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

        Intent intent = new Intent(MainActivity.this, CalculateActivity.class);
        intent.putExtra("exercisesList", exercisesList);
        startActivity(intent);
    }

    // go to specific activity
    public void specificClick(View view) {
        Intent intent = new Intent(MainActivity.this, SpecificActivity.class);
        startActivity(intent);    }

    // go to result activity
    public void resultClick(View view) {
        Intent intent = new Intent(MainActivity.this, ResultActivity.class);
        startActivity(intent);    }

    // go oto reset activity
    public void resetClick(View view) {
        Intent intent = new Intent(MainActivity.this, ResetActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);    }

    // go to info activity
    public void infoClick(View view) {
        Intent intent = new Intent(MainActivity.this, InfoActivity.class);
        startActivity(intent);    }

    // go to trophy activity
    public void trophyClick(View view) {
        Intent intent = new Intent(MainActivity.this, TrophyActivity.class);
        startActivity(intent);    }
}
