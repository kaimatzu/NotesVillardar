package com.kt.notesvillardar;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import java.util.List;

public class DeleteNoteDialogFragment extends DialogFragment {
    DeleteNoteDialogListener listener;
    String note;
    List<Note> notes;
    Note nt;

    public DeleteNoteDialogFragment(String note, List<Note> notes, Note nt) {
        this.note = note;
        this.notes = notes;
        this.nt = nt;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (DeleteNoteDialogFragment.DeleteNoteDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() +
                    " must implement DeleteNoteDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setMessage("Do you want to delete " + note + "?")
                .setView(inflater.inflate(R.layout.deletedialog_layout, null))
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listener.onDeleteListenerMethod(DeleteNoteDialogFragment.this);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listener.onCancelListenerMethod(DeleteNoteDialogFragment.this);
            }
        });
        return builder.create();
    }

    public interface DeleteNoteDialogListener {
        public void onDeleteListenerMethod(DialogFragment dialog);
        public void onCancelListenerMethod(DialogFragment dialog);
    }
}
