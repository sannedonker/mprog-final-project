package com.example.gebruiker.tafelsoefenen.Activities;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.gebruiker.tafelsoefenen.R;

import java.util.ArrayList;

public class SpecificActivity extends AppCompatActivity {

    // initialize variables and list with button ids
    int buttons[] = {R.id.times1, R.id.times2, R.id.times3, R.id.times4, R.id.times5, R.id.times6,
                     R.id.times7, R.id.times8, R.id.times9, R.id.times10};

    ArrayList<Integer> practiceList = new ArrayList<>();
    ArrayList<Integer> exercisesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific);
    }

    // add or remove multiplication to practice list and change color of clikced button
    public void multiplicationSelected(View view) {

        int id = view.getId();
        for (int i = 0; i < buttons.length; i++) {
            if (id == buttons[i]) {
                if (practiceList.contains(i + 1)) {
                    practiceList.remove(Integer.valueOf(1 + i));
                    findViewById(buttons[i]).setBackgroundTintList(ColorStateList.valueOf(
                            getResources().getColor(R.color.colorPrimary)));
                }
                else {
                    practiceList.add(i + 1);
                    findViewById(buttons[i]).setBackgroundTintList(ColorStateList.valueOf(
                            getResources().getColor(R.color.colorAccent)));
                }
            }
        }
    }


    // send user to CalculateActivity
    public void practiceClick(View view) {

        // checks if user selected at least one multiplication
        if (practiceList.size() == 0) {

            // show toast if user didn't select a multiplication
            Toast.makeText(this,"Selecteer tenminste één tafel",Toast.LENGTH_LONG).show();

        } else {

            // create a list with (amount of exercises, booleans, multiplications that need to be practiced)
            int amount = practiceList.size() * 10;
            exercisesList.add(amount);
            exercisesList.add(0);
            exercisesList.add(0);
            exercisesList.addAll(practiceList);

            // go to calculate activity
            Intent intent = new Intent(SpecificActivity.this, CalculateActivity.class);
            intent.putExtra("exercisesList", exercisesList);
            startActivity(intent);
        }
    }
}
