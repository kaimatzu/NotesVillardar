package com.kt.notesvillardar;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NotesAdapter extends ArrayAdapter<Note>{
    int resource;
    List<Note> notes;
    FragmentManager fm;
    Note current;

    public NotesAdapter(@NonNull Context context, int resource, @NonNull List<Note> objects, FragmentManager fm) {
        super(context, resource, objects);
        this.resource = resource;
        this.notes = objects;
        this.fm = fm;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LinearLayout noteView;
        Note note = getItem(position);
        String act_note = note.getNote();
        Date act_created = note.getCreated();
        Date act_modified = note.getCreated();
        if(note.getModified() != null)
            act_modified = note.getModified();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String timeString;

        if(act_modified == act_created)
            timeString = sdf.format(act_created);
        else
            timeString = sdf.format(act_modified);

        if (convertView == null) {
            noteView = new LinearLayout(getContext());
            String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater li = (LayoutInflater) getContext().getSystemService(inflater);
            li.inflate(resource, noteView, true);
        } else {
            noteView = (LinearLayout) convertView;
        }

        TextView tvNote = noteView.findViewById(R.id.tvNote);
        TextView tvTime = noteView.findViewById(R.id.tvTime);
        tvNote.setText(act_note);
        tvTime.setText(timeString);

        CheckBox cbImportant = noteView.findViewById(R.id.cbImportant);
        cbImportant.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                current = note;
                setImportant(isChecked);
            }
        });

        ImageButton btnDelete = noteView.findViewById(R.id.btnDelete);
        btnDelete.setImageResource(android.R.drawable.ic_delete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeleteNoteDialogFragment dialog = new DeleteNoteDialogFragment(act_note, notes, note);
                dialog.show(fm, "DeleteDialog");
                current = note;
                //notes.remove(note);
                //notifyDataSetChanged();
            }
        });

        noteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditNoteDialogFragment dialog = new EditNoteDialogFragment(act_note);
                dialog.show(fm, "EditDialog");
                current = note;
                note.setModified();
                Date act_modified = note.getModified();
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                String timeString = sdf.format(act_modified);
                tvTime.setText(timeString);
                tvTime.setTextColor(Color.BLUE);
            }
        });

        return noteView;
    }

    public void setImportant(boolean important){
        current.important = important;
        Log.d("Important " + current.note, String.valueOf(current.important));
    }

    public void onDeleteListenerMethod(DialogFragment dialog){
        notes.remove(current);
        notifyDataSetChanged();
    }

    public void onEditListenerMethod(DialogFragment dialog) {
        EditText etEdit = dialog.getDialog().findViewById(R.id.etEdit);
        String new_note = etEdit.getText().toString();
        current.setNote(new_note);
        notifyDataSetChanged();
        current = null;
    }

    public void onCancelListenerMethod(DialogFragment dialog) {
        current = null;
    }

//    public void setTimeModified(){
//        current.setModified();
//        Log.d("Current", String.valueOf(current.getModified()));
//        Date act_modified = current.getModified();
//        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
//        String timeString = sdf.format(act_modified);
//        Log.d("Current", timeString);
//    }

}
