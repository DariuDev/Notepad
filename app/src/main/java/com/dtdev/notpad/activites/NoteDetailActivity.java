package com.dtdev.notpad.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import android.os.Bundle;
import android.view.View;

import com.dtdev.notpad.R;
import com.dtdev.notpad.models.Note;

public class NoteDetailActivity extends AppCompatActivity {
    Bundle bundle;
    Note note;

    public NoteDetailActivity (Note note){
        this.note = note;
    }
    public NoteDetailActivity (){

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);
        AppCompatTextView txvTitle = findViewById(R.id.txv_title);
        AppCompatTextView txvDescription = findViewById(R.id.txv_description);
        AppCompatTextView txvTime = findViewById(R.id.txv_time);
        AppCompatTextView txvDate = findViewById(R.id.txv_date);
        AppCompatTextView btnBack = findViewById(R.id.btn_back);

        bundle = getIntent().getExtras();
        String title = bundle.getString("title");
        String description = bundle.getString("description");
        String timeNote = bundle.getString("timeNote");
        String dateNote = bundle.getString("dateNote");

        txvTitle.setText(title);
        txvDescription.setText(description);
        txvTime.setText(timeNote);
        txvDate.setText(dateNote);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}