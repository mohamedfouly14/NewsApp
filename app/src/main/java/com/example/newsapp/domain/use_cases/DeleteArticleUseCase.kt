package com.example.newsapp.domain.use_cases

import com.example.newsapp.data.model.Article
import com.example.newsapp.data.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DeleteArticleUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    operator fun invoke(article: Article)=flow<Boolean>{
        val deletedArticleId=repository.deleteArticle(article)
        if(deletedArticleId!=0)
            emit(true)
        else
            emit(false)
    }.flowOn(Dispatchers.IO)
}