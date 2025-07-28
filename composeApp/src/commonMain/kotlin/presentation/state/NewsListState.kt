package presentation.state

import data.model.NewsArticle

data class NewsListState(
    val isLoading: Boolean = false,
    val articles: List<NewsArticle> = emptyList(),
    val error: String? = null
)