package com.lesnyg.test2movieinfoproject;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.lesnyg.test2movieinfoproject.models.Result;

import java.util.List;


@Dao
public interface ResultDao {
    @Query("SELECT * FROM result ")
    LiveData<List<Result>> getAll();

    @Insert
    void insertFavorite(Result result);

    @Delete
    void deleteFavorite(Result result);

}
