package com.dtdev.notpad.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.dtdev.notpad.R;
import com.dtdev.notpad.adapter.NoteAdapter;
import com.dtdev.notpad.database.NoteDatabaseAdapter;

public class SearchActivity extends AppCompatActivity {

    EditText editSearch;
    NoteDatabaseAdapter noteDatabaseAdapter;
    ListView listSearch;
    ArrayAdapter<String> arrayAdapter;
    CardView txv_search;
    RecyclerView recyclerViewNoteList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        editSearch = findViewById(R.id.edt_search);
        noteDatabaseAdapter = new NoteDatabaseAdapter(getApplicationContext());
        txv_search = findViewById(R.id.txv_search);
        listSearch = findViewById(R.id.list_search);
        recyclerViewNoteList = findViewById(R.id.recycler_list_note);
        txv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search = editSearch.getText().toString();
                arrayAdapter = new ArrayAdapter<>(getApplicationContext()
                        , android.R.layout.simple_list_item_1
                        , noteDatabaseAdapter.getSearchNotes(search));
                listSearch.setAdapter(arrayAdapter);

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}