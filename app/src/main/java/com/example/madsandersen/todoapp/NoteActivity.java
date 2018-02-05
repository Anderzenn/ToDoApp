package com.example.madsandersen.todoapp;

import android.content.DialogInterface;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.madsandersen.todoapp.adapters.ItemsArrayAdapter;

import java.util.ArrayList;

public class NoteActivity extends AppCompatActivity {

    ArrayList<String> mItems;
    ItemsArrayAdapter mItemsArrayAdapter;
    EditText mNewItemText;
    ListView mItemsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        mItems = new ArrayList<>();
        mItemsArrayAdapter = new ItemsArrayAdapter(this, mItems);
        mItemsListView = findViewById(R.id.itemsListView);
        mItemsListView.setAdapter(mItemsArrayAdapter);
        mItemsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView item = view.findViewById(R.id.itemText);
                //Log.v(TAG, "getPaintFlags=" + item.getPaintFlags());
                if (item.getPaintFlags() == 16) {
                    item.setPaintFlags(item.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                } else {
                    item.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                }
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void addItem(View view) {
        LayoutInflater li = LayoutInflater.from(this);
        LinearLayout newNoteBaseLayout = (LinearLayout) li.inflate(R.layout.new_item_dialog, null);

        mNewItemText = (EditText) newNoteBaseLayout.getChildAt(0);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mItems.add(mNewItemText.getText().toString());
                mItemsArrayAdapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("Cancel", null).setTitle("New Item");

        builder.setView(newNoteBaseLayout);
        builder.show();
    }
}
