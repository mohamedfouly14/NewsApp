package com.example.newsapp.domain.use_cases

import com.example.newsapp.data.model.Article
import com.example.newsapp.data.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetLocalArticlesUseCase @Inject constructor(
    private val repository: NewsRepository
){
    operator fun invoke()= flow<List<Article>> {
        repository.getAllArticles().collect(){
            emit(it)
        }
    }.flowOn(Dispatchers.IO)
}