package com.example.gebruiker.tafelsoefenen.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.gebruiker.tafelsoefenen.Classes.Exercise;
import com.example.gebruiker.tafelsoefenen.R;

import java.util.ArrayList;

public class ResultListAdapter extends ArrayAdapter {

    private ArrayList<Exercise> multiplications;
    private ArrayList<String> givenAnswers;
    private Exercise currentExercise;

    // set the lists in the adapter
    public ResultListAdapter(@NonNull Context context, int resource,
                             @NonNull ArrayList<Exercise> objects,
                             @NonNull ArrayList<String> answers) {
        super(context, resource, objects);
        multiplications = objects;
        givenAnswers = answers;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // loads new items
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.result_item_calculating,
                    parent, false);
        }

        // get and set question and answer of the items that are shown
        currentExercise = multiplications.get(position);
        TextView exerciseView = ((TextView) convertView.findViewById(R.id.exercise_tv));
        exerciseView.setText(currentExercise.getMultiplication());
        TextView answerView = ((TextView) convertView.findViewById(R.id.answer_tv));
        String test = String.valueOf(currentExercise.getAnswer());
        answerView.setText(test);

        // set given answer
        TextView answerGivenView = ((TextView) convertView.findViewById(R.id.answer_given_tv));
        answerGivenView.setText(givenAnswers.get(position));

        // get and set color of given answer
        getColor(answerGivenView);

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
