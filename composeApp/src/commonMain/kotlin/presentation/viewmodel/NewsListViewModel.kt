package presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import data.repository.NewsRepository
import data.repository.SampleNewsRepository
import presentation.state.NewsListState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsListViewModel(
    private val repository: NewsRepository = SampleNewsRepository()
) {
    var state by mutableStateOf(NewsListState())
        private set

    private val viewModelScope = CoroutineScope(Dispatchers.Main)

    init {
        loadNews()
    }

    private fun loadNews() {
        state = state.copy(isLoading = true, error = null)
        
        viewModelScope.launch {
            try {
                val articles = repository.getNewsList()
                state = state.copy(
                    isLoading = false,
                    articles = articles,
                    error = null
                )
            } catch (e: Exception) {
                state = state.copy(
                    isLoading = false,
                    error = e.message ?: "不明なエラーが発生しました"
                )
            }
        }
    }

    fun refresh() {
        loadNews()
    }
}