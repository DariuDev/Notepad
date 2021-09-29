package com.dtdev.notpad.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NoteDatabase extends SQLiteOpenHelper {

    private final static String DB_NAME = "note.db";
    private final static int VERSION = 1;


    public NoteDatabase(Context context) {
        super(context,DB_NAME,null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String create_table = "create table tbl_note (id Integer Primary Key AUTOINCREMENT , title varchar(25)  " +
                ", description varchar(300) , dateNote varchar (50) , timeNote varchar(50))";
        sqLiteDatabase.execSQL(create_table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
