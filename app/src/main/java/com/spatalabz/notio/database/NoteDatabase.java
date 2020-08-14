package com.spatalabz.notio.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.spatalabz.notio.dao.NoteDao;
import com.spatalabz.notio.model.Note;

@Database(entities = Note.class,version = 1,exportSchema = false)
public abstract class NoteDatabase extends RoomDatabase {

   private static NoteDatabase noteDatabase;
   public static synchronized NoteDatabase getNoteDatabase(Context context){
       if(noteDatabase==null){
           noteDatabase= Room.databaseBuilder(
                   context.getApplicationContext(),
                   NoteDatabase.class,"notes_db").allowMainThreadQueries().build();



       }
       return noteDatabase;
   }

   public abstract NoteDao noteDao();

}
