package com.example.newsapp.data.data_source.datasources

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsapp.data.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsLocalDataSource {
    suspend fun saveArticle(article: Article):Long

    fun getAllArticles(): Flow<List<Article>>

    suspend fun deleteArticle(article: Article):Int
}