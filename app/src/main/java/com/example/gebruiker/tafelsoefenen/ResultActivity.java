package com.example.gebruiker.tafelsoefenen;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.LineChartView;
import lecho.lib.hellocharts.view.PieChartView;

public class ResultActivity extends AppCompatActivity {

    private DatabaseHelper db;
    private PieChartAdapter adapter;

    ArrayList<Integer> testList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // get updated database and adapter
        db = DatabaseHelper.getInstance(getApplicationContext());
        adapter = new PieChartAdapter(db.selectLevel());

        // set the listview
        ListView lv = findViewById(R.id.result_listview);
        lv.setAdapter(adapter);

//        int charts[] = {R.id.chart1, R.id.chart2, R.id.chart3, R.id.chart4, R.id.chart5,
//                             R.id.chart6, R.id.chart7, R.id.chart8, R.id.chart9, R.id.chart10};
//
//        for (int i = 0; i < charts.length; i++) {
//            PieChartView pieChartView = findViewById(charts[i]);
//
//            List<SliceValue> pieData = new ArrayList<>();
//            Context context = getApplicationContext();
//
//            pieData.add(new SliceValue(2, context.getResources().getColor(R.color.grey)));
//            pieData.add(new SliceValue(3, context.getResources().getColor(R.color.green)));
//            pieData.add(new SliceValue(1, context.getResources().getColor(R.color.yellow)));
//            pieData.add(new SliceValue(0, context.getResources().getColor(R.color.orange)));
//            pieData.add(new SliceValue(4, context.getResources().getColor(R.color.red)));
//
//            PieChartData pieChartData = new PieChartData(pieData);
//            pieChartView.setPieChartData(pieChartData);
//         }



//        PieChartView pieChartView = findViewById(R.id.chart);
//
////        Log.d("test", "bindView: " + grey + " " + green + " " + yellow + " " + orange + " " + red);
//
//        List<SliceValue> pieData = new ArrayList<>();
//        Context context = getApplicationContext();
//
//        pieData.add(new SliceValue(2, context.getResources().getColor(R.color.grey)));
//        pieData.add(new SliceValue(3, context.getResources().getColor(R.color.green)));
//        pieData.add(new SliceValue(1, context.getResources().getColor(R.color.yellow)));
//        pieData.add(new SliceValue(0, context.getResources().getColor(R.color.orange)));
//        pieData.add(new SliceValue(4, context.getResources().getColor(R.color.red)));
//
////        Log.d("test", "bindView: pieData " + pieData);
//
//        PieChartData pieChartData = new PieChartData(pieData);
//        pieChartView.setPieChartData(pieChartData);

    }
}
