package data.repository

import data.model.NewsArticle

interface NewsRepository {
    suspend fun getNewsList(): List<NewsArticle>
}