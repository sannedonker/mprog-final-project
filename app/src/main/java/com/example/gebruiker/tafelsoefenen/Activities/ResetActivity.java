package com.example.gebruiker.tafelsoefenen.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.gebruiker.tafelsoefenen.DatabaseHelper;
import com.example.gebruiker.tafelsoefenen.R;
import com.example.gebruiker.tafelsoefenen.TrophyDatabaseHelper;

public class ResetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);
    }

    // reset database
    public void doReset(View view) {

        // reset databases to original databases
        DatabaseHelper dbExercise = DatabaseHelper.getInstance(getApplicationContext());
        dbExercise.resetLevel();
        TrophyDatabaseHelper dbTrophy = TrophyDatabaseHelper.getInstance(getApplicationContext());
        dbTrophy.resetTrophies();

        // show toast that reset was succesfull
        Toast.makeText(this,"Je resultaten en trofeeën zijn gewist.",Toast.LENGTH_LONG)
                .show();

        // return to MainActivity
        Intent intent = new Intent(ResetActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    // return to MainActivity
    public void dontReset(View view) {
        Intent intent = new Intent(ResetActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}
