package com.dtdev.notpad.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.dtdev.notpad.models.Note;

import java.util.ArrayList;
import java.util.List;

public class NoteDatabaseAdapter extends NoteDatabase {
    NoteDatabase noteDatabase;
    Context context;
    SQLiteDatabase sqLiteDatabase;
    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    public static final String TIME_NOTE = "timeNote";
    public static final String DATE_NOTE = "dateNote";
    public static final String TABLE_NAME = "tbl_note";


    public NoteDatabaseAdapter(Context context) {
        super(context);
        noteDatabase = new NoteDatabase(context);
    }

    public void openDB() {
        try {
            sqLiteDatabase = getWritableDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeDB() {
        try {
            sqLiteDatabase.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public long insertNote(Note note) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TITLE, note.getTitle());
        contentValues.put(DESCRIPTION, note.getDescription());
        contentValues.put(TIME_NOTE, note.getTime_note());
        contentValues.put(DATE_NOTE, note.getDate_note());
        contentValues.put(ID, note.getId());

        return sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
    }

    public long updateNote(int noteId, Note note) {

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TITLE, note.getTitle());
        contentValues.put(DESCRIPTION, note.getDescription());
        contentValues.put(TIME_NOTE, note.getTime_note());
        contentValues.put(DATE_NOTE, note.getDate_note());

        return sqLiteDatabase.update(TABLE_NAME, contentValues, ID + note, null);
    }

    public List<String> getAllNotes() {

        List<String> array = new ArrayList<>();

        SQLiteDatabase   sqLiteDatabase = getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("select * from tbl_note", null);
        while (cursor.moveToNext()) {

            int id = cursor.getInt(0);
            String title = cursor.getString(1);
            String description = cursor.getString(cursor.getColumnIndex("description"));
            String dateNote = cursor.getString(cursor.getColumnIndex("dateNote"));
            String timeNote = cursor.getString(cursor.getColumnIndex("timeNote"));

            array.add(id + "." + title);

        }
        return array;
    }

    public List<String> getSearchNotes(String text) {

        List<String> array = new ArrayList<>();

        SQLiteDatabase  sqLiteDatabase = getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("select * from tbl_note where title Like '%" + text + "%'", null);
        while (cursor.moveToNext()) {

            int id = cursor.getInt(0);
            String title = cursor.getString(1);
            String description = cursor.getString(cursor.getColumnIndex("description"));
            String dateNote = cursor.getString(cursor.getColumnIndex("dateNote"));
            String timeNote = cursor.getString(cursor.getColumnIndex("timeNote"));

            array.add(id + "." + title);

        }
        return array;
    }

    public List<Note> getNotes() {

        List<Note> noteList = new ArrayList<>();

        SQLiteDatabase  sqLiteDatabase = getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("select * from tbl_note", null);
        while (cursor.moveToNext()) {

            int id = cursor.getInt(0);
            String title = cursor.getString(1);
            String description = cursor.getString(cursor.getColumnIndex("description"));
            String dateNote = cursor.getString(cursor.getColumnIndex("dateNote"));
            String timeNote = cursor.getString(cursor.getColumnIndex("timeNote"));

            Note note = new Note();

            note.setId(id);
            note.setTitle(title);
            note.setDate_note(dateNote);
            note.setTime_note(timeNote);
            note.setDescription(description);

            noteList.add(note);
        }
        return noteList;
    }

    public int deleteAllNotes() {
        SQLiteDatabase  sqLiteDatabase = getWritableDatabase();

        return sqLiteDatabase.delete(TABLE_NAME, null, null);
    }


    public int delete(int id) {
       SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        return  sqLiteDatabase.delete(TABLE_NAME, ID + "=" + id, null);
    }

}
