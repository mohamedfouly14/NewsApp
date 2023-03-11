package com.example.newsapp.data.repository

import com.example.newsapp.data.data_source.datasources.NewsRemoteDataSource
import com.example.newsapp.data.model.NewsResponse
import com.example.newsapp.data.model.Source
import retrofit2.Response
import javax.inject.Inject

class NewsRepositoryImp @Inject constructor(
    private val remoteDataSource: NewsRemoteDataSource
):NewsRepository {
    override suspend fun getNews(
        querySearch: String?,
        country: String?,
        sources: Source?
    ): Response<NewsResponse> {
        return remoteDataSource.getNews(querySearch,country,sources)
    }
}