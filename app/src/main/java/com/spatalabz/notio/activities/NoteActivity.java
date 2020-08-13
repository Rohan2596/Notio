package com.spatalabz.notio.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.spatalabz.notio.R;


public class NoteActivity extends AppCompatActivity {

    ImageView save_icon;
    ImageView important_notes;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        save_icon=findViewById(R.id.save_icon);
        save_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            if (save_icon.getTag().equals("save_icon_unchecked")){
                save_icon.setImageResource(R.drawable.ic_baseline_check_circle_24);
                save_icon.setTag("save_icon_checked");
                Log.i("Save Check", "onClick:  "+save_icon.getTag());
            }
            else{
                    save_icon.setImageResource(R.drawable.ic_check_icon);
                    save_icon.setTag("save_icon_unchecked");
                    Log.i("Save Check", "onClick:  "+save_icon.getTag());
                }
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

    }
}