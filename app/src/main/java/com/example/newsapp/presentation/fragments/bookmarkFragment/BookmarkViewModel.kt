package com.example.newsapp.presentation.fragments.bookmarkFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.model.Article
import com.example.newsapp.domain.use_cases.DeleteArticleUseCase
import com.example.newsapp.domain.use_cases.GetLocalArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val getLocalArticlesUseCase: GetLocalArticlesUseCase,
    private val deleteArticleUseCase: DeleteArticleUseCase
) :ViewModel(){
    private var job :Job?=null
    private val _allNewsStates=MutableLiveData<List<Article>>(ArrayList())
    var allNewsStates = _allNewsStates
    private val _deleteArticle = MutableLiveData<Boolean>()
    val deleteArticle: LiveData<Boolean> = _deleteArticle

    init {
        geBookmark()
    }

    fun geBookmark(){
        job?.cancel()
        job = getLocalArticlesUseCase().onEach {
            _allNewsStates.value=it

        }.launchIn(viewModelScope)
    }
    fun deleteArticle(article: Article){
        job?.cancel()
        job=deleteArticleUseCase(article).onEach {
            _deleteArticle.value=it
        }.launchIn(viewModelScope)
    }
}