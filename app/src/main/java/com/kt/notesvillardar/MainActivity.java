package com.kt.notesvillardar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import androidx.fragment.app.DialogFragment;

public class MainActivity extends AppCompatActivity implements EditNoteDialogFragment.EditNoteDialogListener, DeleteNoteDialogFragment.DeleteNoteDialogListener{
    ArrayList<Note> notes;
    NotesAdapter notes_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setListAdapterMethod();
        btnAddListenerMethod();
        etNoteEnterListenerMethod();
    }

    private void etNoteEnterListenerMethod() {
        EditText etNote = findViewById(R.id.etNote);
        etNote.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN ||
                        keyEvent.getAction() == KeyEvent.KEYCODE_ENTER ||
                        keyEvent.getAction() == KeyEvent.KEYCODE_NUMPAD_ENTER) {
                    String note = etNote.getText().toString();
                    notes.add(new Note(note));
                    notes_adapter.notifyDataSetChanged();
                    etNote.setText("");
                    return true;
                }
                return false;
            }
        });
    }

    private void btnAddListenerMethod() {
        Button btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText etNote = findViewById(R.id.etNote);
                String note = etNote.getText().toString();
                notes.add(new Note(note));
                notes_adapter.notifyDataSetChanged();
                etNote.setText("");
            }
        });
    }

    private void setListAdapterMethod() {
        ListView lvList = findViewById(R.id.lvList);
        notes = new ArrayList<>();
        notes.add(new Note("First Note"));
        notes.add(new Note("Second Note"));

        notes_adapter = new NotesAdapter(getBaseContext(), R.layout.note_layout, notes, getSupportFragmentManager());
        lvList.setAdapter(notes_adapter);

        notes.add(new Note("Karl John Villardar"));
    }

    @Override
    public void onEditListenerMethod(DialogFragment dialog) {
        notes_adapter.onEditListenerMethod(dialog);
    }

    @Override
    public void onCancelListenerMethod(DialogFragment dialog) {
        notes_adapter.onCancelListenerMethod(dialog);
    }

    @Override
    public void onDeleteListenerMethod(DialogFragment dialog) {
        notes_adapter.onDeleteListenerMethod(dialog);
    }
}