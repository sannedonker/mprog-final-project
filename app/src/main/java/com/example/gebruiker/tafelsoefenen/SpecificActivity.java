package com.example.gebruiker.tafelsoefenen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class SpecificActivity extends AppCompatActivity {

    ArrayList<Integer> times = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific);
    }

    public void timesSelected(View view) {
//        int times_number = view.getTe
    }

    public void practiceClick(View view) {
    }
}
