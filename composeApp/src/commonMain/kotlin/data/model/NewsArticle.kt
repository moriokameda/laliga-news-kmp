package data.model

data class NewsArticle(
    val id: String,
    val title: String,
    val summary: String,
    val content: String,
    val imageUrls: List<String>,
    val publishDateTime: String,
    val source: String,
    val originalLanguage: String,
    val isTranslated: Boolean
) {
    val primaryImageUrl: String get() = imageUrls.firstOrNull() ?: ""
}