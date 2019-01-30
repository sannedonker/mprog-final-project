package com.example.gebruiker.tafelsoefenen.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.gebruiker.tafelsoefenen.Activities.ResultActivity;
import com.example.gebruiker.tafelsoefenen.Classes.Exercise;
import com.example.gebruiker.tafelsoefenen.R;

import java.util.ArrayList;

public class ResultListSpecificAdapter extends ArrayAdapter {

    private ArrayList<Exercise> multiplications;
    private Exercise currentExercise;

    // set the list in the adapter
    public ResultListSpecificAdapter(@NonNull Context context, int resource,
                                     @NonNull ArrayList<Exercise> objects) {
        super(context, resource, objects);
        multiplications = objects;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // loads new items
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.result_item, parent,
                    false);
        }

        // get and set question and answer of the items that are shown
        currentExercise = multiplications.get(position);
        TextView exerciseView = ((TextView) convertView.findViewById(R.id.exercise));
        exerciseView.setText(currentExercise.getMultiplication() + " = " + currentExercise.getAnswer());

        // get and set color of given answer
        getColor(exerciseView);

        return convertView;
    }


    // get and set color of given answer
    public void getColor(TextView tv) {

        // get correct color with correctness level
        int correctness = currentExercise.getLevel();
        if (correctness == 1) {
            tv.setTextColor(getContext().getResources().getColor(R.color.green));
        } else if (correctness == 2) {
            tv.setTextColor(getContext().getResources().getColor(R.color.yellow));
        } else if (correctness == 3) {
            tv.setTextColor(getContext().getResources().getColor(R.color.orange));
        } else if (correctness == 4){
            tv.setTextColor(getContext().getResources().getColor(R.color.red));
        } else {
            tv.setTextColor(getContext().getResources().getColor(R.color.grey));
        }
    }
}
