package com.example.newsapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newsapp.data.model.Article

@Database(entities =[Article::class], version = 1, exportSchema = false)
abstract class roomDatabase: RoomDatabase() {
    abstract fun getDao():NewsDao
}