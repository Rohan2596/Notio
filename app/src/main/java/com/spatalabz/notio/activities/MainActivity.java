package com.spatalabz.notio.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.spatalabz.notio.R;
import com.spatalabz.notio.adapter.NotesAdapter;
import com.spatalabz.notio.dao.NoteDao;
import com.spatalabz.notio.database.NoteDatabase;
import com.spatalabz.notio.model.Note;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NotesAdapter.onNoteClickListener {

    FloatingActionButton add_button;
    RecyclerView recyclerViewNotes;
    NotesAdapter notesAdapter;
    List<Note> notesList;
    StaggeredGridLayoutManager notesStaggeredGridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getAllNotes();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add_button=findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toNote=new Intent(MainActivity.this, NoteActivity.class);
                startActivity(toNote);
            }
        });
        recyclerViewNotes=findViewById(R.id.recycler);
        notesStaggeredGridLayoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerViewNotes.setLayoutManager(notesStaggeredGridLayoutManager);
        notesAdapter=new NotesAdapter(MainActivity.this,notesList,this);
        recyclerViewNotes.setAdapter(notesAdapter);
        notesAdapter.notifyDataSetChanged();
    }
    private NoteDao connectionSetup(){
        return NoteDatabase.getNoteDatabase(getApplicationContext()).noteDao();
    }
    private void getAllNotes(){
        final List<Note>[] notes = new List[]{new ArrayList<>()};
        notes[0] =connectionSetup().getAllNotes();
        notesList=notes[0];
        Toast.makeText(this,String.valueOf(notes[0].size()),Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNoteClickListener(int position) {
        Intent toNote=new Intent(MainActivity.this, NoteActivity.class);
        startActivity(toNote);
    }
}