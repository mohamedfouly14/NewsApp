package com.example.newsapp.presentation.fragments.searchFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.Utils.DataState
import com.example.newsapp.data.model.Article
import com.example.newsapp.domain.use_cases.GetNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase
):ViewModel() {
    private var job: Job?= null
    private val _allNewsState= MutableLiveData<DataState<List<Article>>>(DataState())
    var allNewsState: LiveData<DataState<List<Article>>> = _allNewsState
    fun Search(keyword:String){
        _allNewsState.value=DataState(isLoading = true)
        job?.cancel()
        getNewsUseCase(querySearch = keyword).onStart {
            delay(1500)
        }.onEach {
            _allNewsState.value= DataState(data = it?.filterNotNull())
        }.catch { error ->
            _allNewsState.value = DataState(error=error.message.toString())
        }.launchIn(viewModelScope)
    }

}