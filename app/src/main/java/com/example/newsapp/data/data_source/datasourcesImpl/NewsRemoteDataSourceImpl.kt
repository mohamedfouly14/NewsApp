package com.example.newsapp.data.data_source.datasourcesImpl

import com.example.newsapp.data.data_source.datasources.NewsRemoteDataSource
import com.example.newsapp.data.model.NewsResponse
import com.example.newsapp.data.model.Source
import com.example.newsapp.data.network.ApiServices
import retrofit2.Response
import javax.inject.Inject

class NewsRemoteDataSourceImpl @Inject constructor(
    private val api:ApiServices
): NewsRemoteDataSource {
    override suspend fun getNews(
        querySearch: String?,
        country: String?,
        sources: Source?
    ): Response<NewsResponse> {
        return api.getNews(querySearch,country,sources)
    }
}