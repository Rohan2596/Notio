package com.spatalabz.notio.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.spatalabz.notio.R;
import com.spatalabz.notio.model.Note;

import java.util.List;
import java.util.zip.Inflater;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    Context context;
    List<Note> notes;
    private onNoteClickListener onNoteClickListener;


    public NotesAdapter(Context context, List<Note> notes,onNoteClickListener onNoteClickListener){

        this.context=context;
        this.notes=notes;
        this.onNoteClickListener=onNoteClickListener;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View notes=layoutInflater.inflate(R.layout.notes_display,parent,false);
        NotesAdapter.ViewHolder viewHolder=new NotesAdapter.ViewHolder(notes,onNoteClickListener);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.notio_Title.setText(notes.get(position).getTitle());
        holder.notio_Description.setText(notes.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }


    public  static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView notio_Title, notio_Description;
        onNoteClickListener onNoteClickListener;
        public ViewHolder(@NonNull View itemView,NotesAdapter.onNoteClickListener onNoteClickListener) {
            super(itemView);
            this.notio_Title=itemView.findViewById(R.id.noteTitle);
            this.notio_Description=itemView.findViewById(R.id.noteDescription);
            this.onNoteClickListener=onNoteClickListener;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
           onNoteClickListener.onNoteClickListener(getAdapterPosition());
        }
    }

    public interface onNoteClickListener{
        void onNoteClickListener(int position);
    }
}
