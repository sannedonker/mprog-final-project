package com.example.gebruiker.tafelsoefenen.Adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ImageView;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

import com.example.gebruiker.tafelsoefenen.R;

public class TrophyAdapter extends ResourceCursorAdapter {

    // constructor
    public TrophyAdapter (Context context, Cursor cursor) {
        super(context, R.layout.trophy_item, cursor);
    }


    // set info of trophy to the bindview
    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        ImageView trophy = view.findViewById(R.id.trophy);
        int lock = context.getResources().getIdentifier("lock","drawable",
                                                        context.getPackageName());

        // set correct trophy if trophy is earned
        if (cursor.getInt(cursor.getColumnIndex("earned")) == 1) {
            trophy.setImageResource(cursor.getInt(cursor.getColumnIndex("drawableId")));
        } else {
            trophy.setImageResource(lock);
        }

        // set name of trophy
        TextView name = view.findViewById(R.id.trophy_name);
        name.setText(cursor.getString(1));
    }
}
