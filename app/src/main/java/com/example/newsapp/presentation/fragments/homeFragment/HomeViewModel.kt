package com.example.newsapp.presentation.fragments.homeFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.Utils.DataState
import com.example.newsapp.Utils.NewsSections
import com.example.newsapp.data.model.Article
import com.example.newsapp.domain.use_cases.GetNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase
):ViewModel(){

    private var job: Job? = null
    private val _allNewsState = MutableLiveData<DataState<List<Article>>>(DataState())
    val allNewsState: LiveData<DataState<List<Article>>> = _allNewsState

init {
    getNews()
}
    private fun getNews() {
        _allNewsState.value = DataState(true)
        job?.cancel()
        job=getNewsUseCase(country = "eg")
            .buffer()
            .onEach {
            _allNewsState.value=DataState(data = it?.filterNotNull())
        }.catch { erorr ->
            _allNewsState.value=DataState(error =erorr.message.toString())
        }.launchIn(viewModelScope)

        }

}