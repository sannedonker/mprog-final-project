package com.example.gebruiker.tafelsoefenen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void beginClick(View view) {
//        TODO: ga naar begin activity
//        TODO: hele database meegeven
    }

    public void calculateClick(View view) {
        Intent intent = new Intent(MainActivity.this, CalculateActivity.class);
        startActivity(intent);
//        TODO: 20 sommen meegeven
//        TODO (in de calculateactivity): database update voor die 20 sommen
    }

    public void specificClick(View view) {
//        TODO: ga naar specific activity
    }

    public void resultClick(View view) {
//        TODO: ga naar result activity
    }

    public void resetClick(View view) {
//        TODO: ga naar reset activity
    }

    public void infoClick(View view) {
//        TODO: ga naar info activity
    }

}
