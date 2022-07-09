package com.kt.notesvillardar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> notes;
    ArrayAdapter<String> notes_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setListAdapterMethod();
        btnAddListenerMethod();
    }

    private void btnAddListenerMethod() {
        Button btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText etNote = findViewById(R.id.etNote);
                String note = etNote.getText().toString();
                notes.add(note);
                notes_adapter.notifyDataSetChanged();
                etNote.setText("");
            }
        });
    }

    private void setListAdapterMethod() {
        ListView lvList = findViewById(R.id.lvList);
        notes = new ArrayList<>();
        notes.add("First Note");
        notes.add("Second Note");

        notes_adapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_list_item_1, notes);
        lvList.setAdapter(notes_adapter);

        notes.add("Karl John Villardar");
    }
}