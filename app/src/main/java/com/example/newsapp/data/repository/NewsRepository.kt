package com.example.newsapp.data.repository

import com.example.newsapp.data.model.NewsResponse
import com.example.newsapp.data.model.Source
import retrofit2.Response

interface NewsRepository {
    suspend fun getNews(querySearch: String? = null,
                        country: String? = null,
                        sources: Source? = null,): Response<NewsResponse>
}