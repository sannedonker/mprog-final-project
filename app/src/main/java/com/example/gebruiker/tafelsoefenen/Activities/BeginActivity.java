package com.example.gebruiker.tafelsoefenen.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.gebruiker.tafelsoefenen.R;

import java.util.ArrayList;
import java.util.Arrays;

public class BeginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begin);
    }


    public void beginLevelClick(View view) {

        // create a list with (amount of exercises, booleans, multiplications that need to be practiced)
        ArrayList<Integer> exercisesList = new ArrayList<>();
        exercisesList.addAll(Arrays.asList(100, 1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

        // send user to CalculateActivity
        Intent intent = new Intent(BeginActivity.this, CalculateActivity.class);
        intent.putExtra("exercisesList", exercisesList);
        startActivity(intent);
    }
}
