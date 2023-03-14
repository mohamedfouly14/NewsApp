package com.example.newsapp.data.di

import com.example.newsapp.data.data_source.datasources.NewsLocalDataSource
import com.example.newsapp.data.data_source.datasources.NewsRemoteDataSource
import com.example.newsapp.data.data_source.datasourcesImpl.NewsLocalDataSourceImpl
import com.example.newsapp.data.data_source.datasourcesImpl.NewsRemoteDataSourceImpl
import com.example.newsapp.data.database.NewsDao
import com.example.newsapp.data.network.ApiServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule{
    @Singleton
    @Provides
    fun provideNewsRemoteDataSource(
        apiServices: ApiServices
    ):NewsRemoteDataSource{
        return NewsRemoteDataSourceImpl(apiServices)
    }

    @Singleton
    @Provides
    fun provideNewsLocalDataSource(
        dao: NewsDao
    ):NewsLocalDataSource{
        return NewsLocalDataSourceImpl(dao)
    }
}