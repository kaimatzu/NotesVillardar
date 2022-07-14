package com.kt.notesvillardar;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Note {
    String note;
    Date created, modified;
    boolean important;

    public Note(String note) {
        this.note = note;
        created = new Date(System.currentTimeMillis());
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setModified() { this.modified = new Date(System.currentTimeMillis()); }

    public Date getModified() { return modified; }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String timeString = sdf.format(created);
        return "(" + timeString + ") " + note;
    }
}
