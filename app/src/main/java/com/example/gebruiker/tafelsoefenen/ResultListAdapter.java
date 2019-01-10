package com.example.gebruiker.tafelsoefenen;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

public class ResultListAdapter extends ResourceCursorAdapter {

    public ResultListAdapter(Context context, int layout, Cursor c) {
        super(context, R.layout.result_item, c);
    }



    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView exercise_view = view.findViewById(R.id.exercise);
//        exercise_view.setText();
        // TODO: de goede som selecteren
    }
}
