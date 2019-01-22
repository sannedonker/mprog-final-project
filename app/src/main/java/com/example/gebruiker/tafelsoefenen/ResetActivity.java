package com.example.gebruiker.tafelsoefenen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ResetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);
    }

    public void doReset(View view) {

        // reset database to original database
        DatabaseHelper dbExercise = DatabaseHelper.getInstance(getApplicationContext());
        dbExercise.resetLevel();

        TrophyDatabaseHelper dbTrophy = TrophyDatabaseHelper.getInstance(getApplicationContext());
        dbTrophy.resetTrophies();

        Toast.makeText(this,"Je resultaten en trofeeën zijn gewist.",Toast.LENGTH_LONG).show();

        Intent intent = new Intent(ResetActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    // return to main activity
    public void dontReset(View view) {
        Intent intent = new Intent(ResetActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}
