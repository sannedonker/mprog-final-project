package com.example.gebruiker.tafelsoefenen;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ResourceCursorAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

public class PieChartAdapter extends BaseAdapter {

    private final ArrayList levels = new ArrayList();
    int grey, green, yellow, orange, red;

    Context context;

    public PieChartAdapter(Map<Integer, ArrayList<Integer>> levelMap) {

        // add both key and values to levels
        levels.addAll(levelMap.entrySet());
    }

    @Override
    public int getCount() {
        return levels.size();
    }

    @Override
    public Map.Entry<Integer, ArrayList<Integer>> getItem(int position) {
        return (Map.Entry) levels.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO implement you own logic with ID
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View result;

        if (convertView == null) {
            result = LayoutInflater.from(parent.getContext()).inflate(R.layout.pie_chart, parent, false);
        } else {
            result = convertView;
        }

        Map.Entry<Integer, ArrayList<Integer>> multiplication = getItem(position);
//        Log.d("test", "getView: wat is multiplication " + multiplication);

        ArrayList<Integer> actualLevels = multiplication.getValue();
        String key = multiplication.getKey().toString();

//        Log.d("test", "getView: wat is actualLevels " + actualLevels);

        // count amounts of colors
        grey = 0;
        green = 0;
        yellow = 0;
        orange = 0;
        red = 0;
        for (int i = 0; i < actualLevels.size(); i++) {
//            Log.d("test", "getView: hoe vaak kom ik hier " + counter);
            int level = actualLevels.get(i);
            if (level == 0) {
                grey++;
            } else if (level == 1) {
                green++;
            } else if (level == 2) {
                yellow++;
            } else if (level == 3) {
                orange++;
            } else {
                red++; }
        }

        PieChartView pieChartView = result.findViewById(R.id.chart);

        pieChartView.setInteractive(false);

//        Log.d("test", "bindView: " + grey + " " + green + " " + yellow + " " + orange + " " + red);

        List<SliceValue> pieData = new ArrayList<>();
        context = parent.getContext();

        pieData.add(new SliceValue(green, context.getResources().getColor(R.color.green)));
        pieData.add(new SliceValue(yellow, context.getResources().getColor(R.color.yellow)));
        pieData.add(new SliceValue(orange, context.getResources().getColor(R.color.orange)));
        pieData.add(new SliceValue(red, context.getResources().getColor(R.color.red)));
        pieData.add(new SliceValue(grey, context.getResources().getColor(R.color.grey)));

        Log.d("test", "bindView: pieData " + pieData);

        PieChartData pieChartData = new PieChartData(pieData);
        pieChartData.setHasCenterCircle(true).setCenterText1(key);
        pieChartView.setPieChartData(pieChartData);

        return result;
    }
}

