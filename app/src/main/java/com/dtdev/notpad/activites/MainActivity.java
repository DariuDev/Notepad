package com.dtdev.notpad.activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.dtdev.notpad.R;
import com.dtdev.notpad.adapter.NoteAdapter;
import com.dtdev.notpad.adapter.SwipeHelper;
import com.dtdev.notpad.database.NoteDatabase;
import com.dtdev.notpad.database.NoteDatabaseAdapter;
import com.dtdev.notpad.models.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    NoteDatabase noteDatabase;
    ListView lstData;
    NoteDatabaseAdapter noteDatabaseAdapter;
    DrawerLayout drawerLayout;
    RecyclerView recyclerViewNoteList;
    List<Note> noteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_pad);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        recyclerViewNoteList = findViewById(R.id.recycler_list_note);
        /* drawerLayout = findViewById(R.id.drawer_layout);*/

        noteDatabase = new NoteDatabase(getApplicationContext());
        noteDatabaseAdapter = new NoteDatabaseAdapter(getApplicationContext());

        FloatingActionButton btn_add_note = findViewById(R.id.btn_add_note);
        btn_add_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intentAdd = new Intent(getApplicationContext(), AddNoteActivity.class);
                startActivity(intentAdd);
            }
        });



     /*   ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(MainActivity.this,
                drawerLayout,
                toolbar,
                R.string.open, R.string.close);
        toggle.syncState();*/
    }

    @Override
    protected void onResume() {
        super.onResume();
        recyclerViewNoteList.setAdapter(new NoteAdapter(noteDatabaseAdapter.getNotes()
                , getApplicationContext()));

        recyclerViewNoteList.setLayoutManager(new LinearLayoutManager(getApplicationContext()
                , RecyclerView.VERTICAL, false));
/*        NoteAdapter noteAdapter = new NoteAdapter(noteList, this);
        ItemTouchHelper.Callback callback = new SwipeHelper(noteAdapter);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(recyclerViewNoteList);*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.option_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.about:
                break;
            case R.id.contact:
                break;
            case R.id.search:
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);
                break;

        }
        return super.onOptionsItemSelected(item);
    }


}
