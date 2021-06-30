package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class itemAdapter extends ArrayAdapter<item> {
    private int newResourceId;

    public itemAdapter(Context context, int resourceId, List<item> itemList){
        super(context, resourceId, itemList);
        newResourceId = resourceId;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        item item = getItem (position);
        View view = LayoutInflater.from (getContext ()).inflate (newResourceId, parent, false);

        TextView title = view.findViewById (R.id.title);
        TextView description = view.findViewById (R.id.description);
//        ImageView img = view.findViewById(R.id.img);

        title.setText (item.getTitle());
        description.setText(item.getDescription());
//        img.setImageResource(item.getImage_id());
        return view;
    }

}
