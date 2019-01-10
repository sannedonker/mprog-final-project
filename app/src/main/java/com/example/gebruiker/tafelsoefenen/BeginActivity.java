package com.example.gebruiker.tafelsoefenen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class BeginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begin);
    }

    public void beginLevelClick(View view) {

        // create a list with (amount of exercises, boolean, multiplications that need to be practiced)
//        ArrayList<Integer> multiplications = new ArrayList<>();
//        multiplications.add(100);
//        multiplications.add(0);
//        for (int i = 0; i < 10; i ++) {
//            multiplications.add(i + 1);
//        }

        // create a list with (amount of exercises, boolean, multiplications that need to be practiced)
        int exercisesList[] = {100, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        Intent intent = new Intent(BeginActivity.this, CalculateActivity.class);
        intent.putExtra("exercisesList", exercisesList);
        startActivity(intent);
    }
}
