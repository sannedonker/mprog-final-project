package com.example.gebruiker.tafelsoefenen;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

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

        // set trophy if trophy is earned
        if (cursor.getInt(4) == 1) {
            trophy.setImageResource(cursor.getInt(3));
        } else {
            trophy.setImageResource(lock);
        }

        // set name of trophy
        TextView name = view.findViewById(R.id.trophy_name);
        name.setText(cursor.getString(1));
    }
}
