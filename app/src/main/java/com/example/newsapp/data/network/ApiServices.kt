package com.example.newsapp.data.network

import com.example.newsapp.Utils.Constants.API_KEY
import com.example.newsapp.data.model.NewsResponse
import com.example.newsapp.data.model.Source
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET("v2/top-headlines")
    suspend fun getNews(
        @Query("q") querySearch: String?=null,
        @Query("country") country: String?=null,
        @Query("sources") sources: Source?=null,
        @Query("apiKey") apiKey: String=API_KEY
    ):Response<NewsResponse>

}