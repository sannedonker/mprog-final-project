package com.example.gebruiker.tafelsoefenen.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.gebruiker.tafelsoefenen.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

// TODO: VERWIJZEN NAAR DEZE LINK: https://stackoverflow.com/questions/19466757/hashmap-to-listview/19467717#19467717

public class PieChartAdapter extends BaseAdapter {

    private final ArrayList levels = new ArrayList();
    private int grey, green, yellow, orange, red;
    private String key;
    private ArrayList<Integer> actualLevels;

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


    // method required but not actually needed for this application
    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View result;

        if (convertView == null) {
            result = LayoutInflater.from(parent.getContext()).inflate(R.layout.pie_chart, parent,
                    false);
        } else {
            result = convertView;
        }

        // get correct multiplication and corresponding results
        Map.Entry<Integer, ArrayList<Integer>> multiplication = getItem(position);
        actualLevels = multiplication.getValue();
        key = multiplication.getKey().toString();

        // count the amount of every color in the pieChart
        countColors();

        // set the data in the pieChart
        makePieChart(parent, result);

        return result;
    }

    // count how much of every color is represented in the pieChart
    public void countColors() {

        // set amount of colors to zero
        grey = 0;
        green = 0;
        yellow = 0;
        orange = 0;
        red = 0;

        // increase amount of color if it corresponds with the level
        for (int i = 0; i < actualLevels.size(); i++) {
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
    }

    // set pieChartData
    public void makePieChart(ViewGroup parent, View result) {

        // set color values to pieData
        List<SliceValue> pieData = new ArrayList<>();
        context = parent.getContext();
        pieData.add(new SliceValue(green, context.getResources().getColor(R.color.green)));
        pieData.add(new SliceValue(yellow, context.getResources().getColor(R.color.yellow)));
        pieData.add(new SliceValue(orange, context.getResources().getColor(R.color.orange)));
        pieData.add(new SliceValue(red, context.getResources().getColor(R.color.red)));
        pieData.add(new SliceValue(grey, context.getResources().getColor(R.color.grey)));

        // set pieData to the pieChart
        PieChartData pieChartData = new PieChartData(pieData);
        pieChartData.setHasCenterCircle(true).setCenterText1(key);

        PieChartView pieChartView = result.findViewById(R.id.chart);
        pieChartView.setPieChartData(pieChartData);

        // make sure that pie charts can't be rotated
        pieChartView.setInteractive(false);

    }
}

