package com.dtdev.notpad.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.dtdev.notpad.database.NoteDatabaseAdapter;
import com.dtdev.notpad.models.Note;

import java.util.ArrayList;
import java.util.List;

public class SwipeHelper extends ItemTouchHelper.SimpleCallback {

    NoteAdapter adapter;
    List<Note> noteList;

    public SwipeHelper(int dragDirs, int swipeDirs) {
        super(dragDirs, swipeDirs);
    }

    public SwipeHelper(NoteAdapter adapter) {
        super(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT);
        this.adapter = adapter;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView,
                          RecyclerView.ViewHolder viewHolder,
                          RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

       /* removeItem((long) viewHolder.itemView.getTag());
        NoteAdapter noteAdapter = new NoteAdapter(noteList, adapter.context);
        noteAdapter.deleteItem( direction);*/
    }

    private void removeItem(long id) {

    }
}