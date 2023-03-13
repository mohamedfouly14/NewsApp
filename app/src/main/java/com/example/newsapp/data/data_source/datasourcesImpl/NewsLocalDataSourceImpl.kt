package com.example.newsapp.data.data_source.datasourcesImpl

import com.example.newsapp.data.data_source.datasources.NewsLocalDataSource
import com.example.newsapp.data.database.NewsDao
import com.example.newsapp.data.model.Article
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsLocalDataSourceImpl @Inject constructor(
    private val dao: NewsDao
) :NewsLocalDataSource{
    override suspend fun saveArticle(article: Article): Long {
        return dao.saveArticle(article)
    }

    override fun getAllArticles(): Flow<List<Article>> {
        return dao.getAllArticles()
    }

    override suspend fun deleteArticle(article: Article): Int {
        return dao.deleteArticle(article)
    }

}