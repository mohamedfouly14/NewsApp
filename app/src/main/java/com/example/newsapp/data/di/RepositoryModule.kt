package com.example.newsapp.data.di

import com.example.newsapp.data.data_source.datasources.NewsRemoteDataSource
import com.example.newsapp.data.repository.NewsRepository
import com.example.newsapp.data.repository.NewsRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideRepository(
        remoteDataSource: NewsRemoteDataSource
    ):NewsRepository{
        return NewsRepositoryImp(remoteDataSource)
    }
}