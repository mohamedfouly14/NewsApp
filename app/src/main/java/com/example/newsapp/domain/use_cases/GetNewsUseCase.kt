package com.example.newsapp.domain.use_cases

import com.example.newsapp.Utils.Constants.SUCCESS_CODE
import com.example.newsapp.data.model.Article
import com.example.newsapp.data.model.Source
import com.example.newsapp.data.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val repository: NewsRepository
){
    operator fun invoke(
        querySearch: String? = null,
        country: String? = null,
        sources: Source? = null,
    ): Flow<List<Article?>?> = flow {
        try {
            val response = repository.getNews(querySearch, country, sources)
            if (response.code() == SUCCESS_CODE) {
                response.body()?.let { it ->
                    emit(it.articles)
                    return@flow
                }
                throw java.lang.Exception("Not Found Data")
            } else {
                response.errorBody()?.let {
                    throw Exception(it.string())
                }
                throw Exception(response.message())
            }

        } catch (http: HttpException) {
            throw java.lang.Exception(http.message ?: "something is wrong!, try again")

        } catch (io: IOException) {
            throw java.lang.Exception("No Internet Connection \n ${io.message}")
        }

    }
}