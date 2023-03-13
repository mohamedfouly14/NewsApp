package com.example.newsapp.data.di

import android.app.Application
import androidx.room.Room
import com.example.newsapp.data.database.RDatabase
import com.example.newsapp.data.database.NewsDao
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
    fun provideDatabase(app:Application):RDatabase{
        return Room.databaseBuilder(app,RDatabase::class.java,"news_dp")
            .fallbackToDestructiveMigration()
            .build()
    }
    @Provides
    @Singleton
    fun provideDao(RDatabase: RDatabase):NewsDao{
        return RDatabase.getDao()
    }

}