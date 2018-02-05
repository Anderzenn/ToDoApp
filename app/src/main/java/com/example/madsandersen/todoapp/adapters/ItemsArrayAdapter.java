package com.example.madsandersen.todoapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.madsandersen.todoapp.R;

import java.util.ArrayList;

/**
 * Created by Mads Andersen on 05-02-2018.
 */

public class ItemsArrayAdapter extends ArrayAdapter<String> {

    Context mContext;
    ArrayList<String> mArrayList;

    public ItemsArrayAdapter(Context context, ArrayList<String> arrayList) {
        super(context, R.layout.items_list, arrayList);
        mContext = context;
        mArrayList = arrayList;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.items_list, parent, false);
            holder = new ViewHolder();

            holder.itemName = convertView.findViewById(R.id.itemText);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.itemName.setText(mArrayList.get(position));

        return convertView;
    }

    private static class ViewHolder {
        TextView itemName;
    }
}
