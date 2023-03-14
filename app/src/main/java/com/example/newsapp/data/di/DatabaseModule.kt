package com.example.newsapp.data.di

import android.app.Application
import androidx.room.Room
import com.example.newsapp.data.database.NewsDao
import com.example.newsapp.data.database.roomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(app:Application): roomDatabase {
        return Room.databaseBuilder(app,roomDatabase::class.java,"news_dp")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideDao(Database: roomDatabase):NewsDao{
        return Database.getDao()
    }


}