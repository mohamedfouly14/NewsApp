package com.example.newsapp.data.database

import androidx.room.*
import com.example.newsapp.data.model.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveArticle(article: Article):Long

    @Query("Select * from articles")
    fun getAllArticles(): Flow<List<Article>>

    @Delete
    suspend fun deleteArticle(article: Article):Int
}