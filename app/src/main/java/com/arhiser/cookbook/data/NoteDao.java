package com.arhiser.cookbook.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.arhiser.cookbook.model.Recept;

import java.util.List;

@Dao
public interface NoteDao {

    @Query("SELECT * FROM Recept")
    List<Recept> getAll();

    @Query("SELECT * FROM Recept")
    LiveData<List<Recept>> getAllLiveData();

    @Query("SELECT * FROM Recept WHERE uid IN (:noteIds)")
    List<Recept> loadAllByIds(int[] noteIds);

    @Query("SELECT * FROM Recept WHERE uid = :uid LIMIT 1")
    Recept findById(int uid);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Recept recept);

    @Update
    void update(Recept recept);

    @Delete
    void delete(Recept recept);

}
