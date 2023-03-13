package com.example.newsapp.data.repository

import com.example.newsapp.data.model.Article
import com.example.newsapp.data.model.NewsResponse
import com.example.newsapp.data.model.Source
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface NewsRepository {
    suspend fun getNews(querySearch: String? = null,
                        country: String? = null,
                        sources: Source? = null,): Response<NewsResponse>
    suspend fun saveArticle(article: Article):Long

    fun getAllArticles(): Flow<List<Article>>

    suspend fun deleteArticle(article: Article):Int
}