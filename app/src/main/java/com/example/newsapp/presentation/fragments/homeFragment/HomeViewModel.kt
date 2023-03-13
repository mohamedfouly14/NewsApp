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
    private val _allNewsState = MutableLiveData(DataState<Map<String, List<Article>?>>())
    val allNewsState: LiveData<DataState<Map<String, List<Article>?>>> = _allNewsState

init {
    getNews()
}
    private fun getNews() {
        _allNewsState.value = DataState(true)
        val responseDataMap = HashMap<String, List<Article>?>()
        val allEgyptNews = getNewsUseCase(country = "eg")

        job?.cancel()
        job = allEgyptNews?.onEach {
            responseDataMap[NewsSections.LatestNews.name]= it as List<Article>?
            _allNewsState.value = DataState(data = responseDataMap)
        }?.catch {error->
            _allNewsState.value = DataState(error = error.message.toString())
        }?.launchIn(viewModelScope)

        }

}