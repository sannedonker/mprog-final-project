package com.example.gebruiker.tafelsoefenen;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ResultListSpecificAdapter extends ArrayAdapter {

    private ArrayList<Exercise> multiplications;

    // set the list in the adapter
    public ResultListSpecificAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Exercise> objects) {
        super(context, resource, objects);
        multiplications = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // loads new items
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.result_item, parent, false);
        }

        // get and set question and answer of the items that are shown
        Exercise current_exercise = multiplications.get(position);
        TextView exerciseView = ((TextView) convertView.findViewById(R.id.exercise));
        exerciseView.setText(current_exercise.getMultiplication() + " = " + current_exercise.getAnswer());

        // get correct color with correctness level
        int correctness = current_exercise.getLevel();
        if (correctness == 1) {
            exerciseView.setTextColor(getContext().getResources().getColor(R.color.green));
        } else if (correctness == 2) {
            exerciseView.setTextColor(getContext().getResources().getColor(R.color.yellow));
        } else if (correctness == 3) {
            exerciseView.setTextColor(getContext().getResources().getColor(R.color.orange));
        } else if (correctness == 4){
            exerciseView.setTextColor(getContext().getResources().getColor(R.color.red));
        } else {
            exerciseView.setTextColor(getContext().getResources().getColor(R.color.grey));
        }

        return convertView;
    }
}
