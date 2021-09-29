package com.dtdev.notpad.activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.dtdev.notpad.R;
import com.dtdev.notpad.database.NoteDatabaseAdapter;
import com.dtdev.notpad.models.Note;
import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;

public class AddNoteActivity extends AppCompatActivity {
    Toolbar toolbar;
    AppCompatEditText edt_title, edt_memo;
    TextView btn_back;
    NoteDatabaseAdapter noteDatabaseAdapter;
    Calendar calender;
    String date = "";
    String time = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        edt_title = findViewById(R.id.edt_title);
        edt_memo = findViewById(R.id.edt_memo);
        btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(view -> {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(AddNoteActivity.this);
            alertDialog.setMessage("Are you sure ... !??");
            alertDialog.setIcon(android.R.drawable.ic_delete);
            alertDialog.setTitle("Exit!");

            alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            alertDialog.show();
        });
        noteDatabaseAdapter = new NoteDatabaseAdapter(getApplicationContext());
        calender = Calendar.getInstance();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_add, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.btn_save:
                Note note = new Note();
                note.setTitle(edt_title.getText().toString());
                note.setDescription(edt_memo.getText().toString());
                note.setTime_note(time);
                note.setDate_note(date);
                long result = noteDatabaseAdapter.insertNote(note);
                if (result >= 0) {
                    Toast.makeText(getApplicationContext(),"your note added" , Toast.LENGTH_SHORT).show();
                } else {

                    Toast.makeText(getApplicationContext(),"your note is not added" , Toast.LENGTH_SHORT).show();
                }
                finish();
                break;
            case R.id.btn_time:

                TimePickerDialog timePickerDialog = new TimePickerDialog(AddNoteActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        time = i + ":" + i1;
                    }
                }, calender.get(Calendar.HOUR), calender.get(Calendar.MINUTE), true);
                timePickerDialog.show();
                break;
            case R.id.btn_date:

                DatePickerDialog datePickerDialog = new DatePickerDialog(AddNoteActivity.this,
                        (datePicker, i, i1, i2) -> date = i + "/" + i1 + "/" + i2, calender.get(Calendar.YEAR),
                        calender.get(Calendar.MONTH), calender.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}