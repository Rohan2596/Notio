package com.spatalabz.notio.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.spatalabz.notio.R;
import com.spatalabz.notio.database.NoteDatabase;
import com.spatalabz.notio.model.Note;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class NoteActivity extends AppCompatActivity {

    private ImageView save_icon,important_notes,back_arrow;
    private EditText noteTitle,noteDescription;
    private TextView textDateTime;



    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        save_icon=findViewById(R.id.save_icon);
        save_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                saveNote();
                    }
        });
        important_notes=findViewById(R.id.impNotes);
        important_notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if ( important_notes.getTag().equals("imp_unMarked")){
                    important_notes.setImageResource(R.drawable.ic_imp_true);
                    important_notes.setTag("imp_Marked");
                    Log.i("Save Check", "onClick:  "+important_notes.getTag());
                }
                else{
                    important_notes.setImageResource(R.drawable.ic_imp_unselected_icon);
                    important_notes.setTag("imp_unMarked");
                    Log.i("Save Check", "onClick:  "+important_notes.getTag());
                }

            }
        });

        noteTitle=findViewById(R.id.noteTitle);
        noteDescription=findViewById(R.id.noteDescription);
        textDateTime=findViewById(R.id.note_creation_date);
        textDateTime.setText(
                new SimpleDateFormat("EEEE, dd MMMM yyyy HH:mm a", Locale.getDefault())
                        .format(new Date())
        );

        back_arrow=findViewById(R.id.arrow_back);
        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toMainActivity=new Intent(NoteActivity.this,MainActivity.class);
                startActivity(toMainActivity);
            }
        });

    }

   private void saveNote(){
       final List<Note>[] notes = new List[]{new ArrayList<>()};
       if(noteTitle.getText().toString().trim().isEmpty()){
            Toast.makeText(this,"Note title can't be empty",Toast.LENGTH_SHORT).show();
            return;
        }
        final Note note=new Note();
        note.setTitle(noteTitle.getText().toString());
        note.setDescription(noteDescription.getText().toString());
        note.setCreatedTimeStamp(textDateTime.getText().toString());
       NoteDatabase.getNoteDatabase(getApplicationContext()).noteDao().insertNote(note);
       notes[0] =NoteDatabase.getNoteDatabase(getApplicationContext()).noteDao().getAllNotes();


       Toast.makeText(this,String.valueOf(notes[0].get(0).getTitle()),Toast.LENGTH_SHORT).show();

   }

}
