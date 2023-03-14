package com.example.newsapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newsapp.data.model.Article

@Database(entities =[Article::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class RoomDatabase: RoomDatabase() {
    abstract fun getDao():NewsDao
}