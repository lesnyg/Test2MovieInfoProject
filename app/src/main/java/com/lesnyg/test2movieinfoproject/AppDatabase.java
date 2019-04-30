package com.lesnyg.test2movieinfoproject;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.lesnyg.test2movieinfoproject.models.Result;

@Database(entities = {Result.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ResultDao resultDao();
}