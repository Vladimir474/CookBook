package com.arhiser.cookbook.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.arhiser.cookbook.model.Recept;

@Database(entities = {Recept.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract NoteDao noteDao();
}
