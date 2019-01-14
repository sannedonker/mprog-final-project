package com.example.gebruiker.tafelsoefenen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ResetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);
    }

    public void doReset(View view) {

        // TODO: geeft geen error maar weet niet zeker of het werkt!
        // reset database to original database
        DatabaseHelper db = DatabaseHelper.getInstance(getApplicationContext());
        db.resetLevel();

        // TODO: show confirmation message that the app has been resetted


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
