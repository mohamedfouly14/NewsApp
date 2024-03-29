package com.example.newsapp.presentation.fragments.detailFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.model.Article
import com.example.newsapp.domain.use_cases.SaveArticleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val saveArticleUseCase:SaveArticleUseCase
) :ViewModel(){
    private var job :Job?=null
    private val _saveArticle = MutableLiveData<Boolean>()
    val   saveArticle: LiveData<Boolean> = _saveArticle

    fun saveArticle(article: Article){
        job?.cancel()
        job = saveArticleUseCase(article).onEach {
            _saveArticle.value = it
        }.launchIn(viewModelScope)
    }

}