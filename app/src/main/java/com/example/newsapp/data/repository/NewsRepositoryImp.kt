package com.example.newsapp.data.repository

import com.example.newsapp.data.data_source.datasources.NewsLocalDataSource
import com.example.newsapp.data.data_source.datasources.NewsRemoteDataSource
import com.example.newsapp.data.model.Article
import com.example.newsapp.data.model.NewsResponse
import com.example.newsapp.data.model.Source
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class NewsRepositoryImp @Inject constructor(
    private val remoteDataSource: NewsRemoteDataSource,
    private val newsLocalDataSource: NewsLocalDataSource
):NewsRepository {
    override suspend fun getNews(
        querySearch: String?,
        country: String?,
        sources: Source?
    ): Response<NewsResponse> {
        return remoteDataSource.getNews(querySearch,country,sources)
    }

    override suspend fun saveArticle(article: Article): Long {
        return newsLocalDataSource.saveArticle(article)
    }

    override fun getAllArticles(): Flow<List<Article>> {
        return newsLocalDataSource.getAllArticles()
    }

    override suspend fun deleteArticle(article: Article): Int {
        return newsLocalDataSource.deleteArticle(article)
    }
}