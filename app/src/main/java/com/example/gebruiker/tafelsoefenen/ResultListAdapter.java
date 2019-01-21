package com.example.gebruiker.tafelsoefenen;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ResultListAdapter extends ArrayAdapter {

    private ArrayList<Exercise> multiplications;
    private ArrayList<Integer> givenAnswers;

    // set the lists in the adapter
    public ResultListAdapter(@NonNull Context context, int resource,
                             @NonNull ArrayList<Exercise> objects,
                             @NonNull ArrayList<Integer> answers) {
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
        Exercise current_exercise = multiplications.get(position);
        TextView exerciseView = ((TextView) convertView.findViewById(R.id.exercise_tv));
        exerciseView.setText(current_exercise.getMultiplication());
        TextView answerView = ((TextView) convertView.findViewById(R.id.answer_tv));
        Log.d("test", "getView: answerView" + answerView);
        Log.d("test", "getView: answer " + current_exercise.getAnswer());
        String test = String.valueOf(current_exercise.getAnswer());
        answerView.setText(test);

        // TODO answer opslaan als string (zoals oorspronkelijk) wat ik nu doe is onnodig
        // set given answer
        TextView answerGivenView = ((TextView) convertView.findViewById(R.id.answer_given_tv));
        answerGivenView.setText(givenAnswers.get(position).toString());

        // get correct color with correctness level
        int correctness = current_exercise.getLevel();
        if (correctness == 1) {
            answerGivenView.setTextColor(getContext().getResources().getColor(R.color.green));
        } else if (correctness == 2) {
            answerGivenView.setTextColor(getContext().getResources().getColor(R.color.yellow));
        } else if (correctness == 3) {
            answerGivenView.setTextColor(getContext().getResources().getColor(R.color.orange));
        } else if (correctness == 4){
            answerGivenView.setTextColor(getContext().getResources().getColor(R.color.red));
        } else {
            answerGivenView.setTextColor(getContext().getResources().getColor(R.color.grey));
        }

        return convertView;
    }
}
