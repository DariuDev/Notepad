package com.dtdev.notpad.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Ignore;

import com.dtdev.notpad.R;
import com.dtdev.notpad.activites.NoteDetailActivity;
import com.dtdev.notpad.database.NoteDatabase;
import com.dtdev.notpad.database.NoteDatabaseAdapter;
import com.dtdev.notpad.models.Note;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteAdapterViewHolder> {
    List<Note> noteList;

    Context context;
    ArrayList<ArrayList<String>> notes;

    public NoteAdapter(List<Note> noteList, Context context) {
        this.noteList = noteList;
        this.context = context;
    }

    @NonNull
    @Override
    public NoteAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.note_row, null);

        return new NoteAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapterViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Note note = noteList.get(position);
        holder.txvTitle.setText(note.getTitle());
        holder.txvDescription.setText(note.getDescription());
        holder.cardRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, NoteDetailActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("id", note.getId());
                intent.putExtra("title", note.getTitle());
                intent.putExtra("description", note.getDescription());
                intent.putExtra("timeNote", note.getTime_note());
                intent.putExtra("dateNote", note.getDate_note());
                context.startActivity(intent);
            }
        });

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NoteDatabaseAdapter noteDatabaseAdapter = new NoteDatabaseAdapter(context);
                noteList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeRemoved(position, noteList.size());
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    class NoteAdapterViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView txvTitle;
        AppCompatTextView txvDescription;
        AppCompatImageView imgDelete;
        AppCompatImageView imgShare;
        CardView cardRow;

        public NoteAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            txvTitle = itemView.findViewById(R.id.txv_title);
            txvDescription = itemView.findViewById(R.id.txv_description);
            cardRow = itemView.findViewById(R.id.card_row);
            imgDelete = itemView.findViewById(R.id.img_delete);

            imgShare = itemView.findViewById(R.id.img_share);

        }
    }
}
