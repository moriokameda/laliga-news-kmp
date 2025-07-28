import androidx.compose.runtime.*
import presentation.screen.NewsListScreen
import presentation.screen.NewsDetailScreen
import theme.LaLigaNewsTheme

sealed class Screen {
    object NewsList : Screen()
    data class NewsDetail(val articleId: String) : Screen()
}

@Composable
fun App() {
    var currentScreen by remember { mutableStateOf<Screen>(Screen.NewsList) }
    
    LaLigaNewsTheme {
        when (currentScreen) {
            is Screen.NewsList -> {
                NewsListScreen(
                    onNewsClick = { articleId ->
                        currentScreen = Screen.NewsDetail(articleId)
                    }
                )
            }
            
            is Screen.NewsDetail -> {
                val detailScreen = currentScreen as Screen.NewsDetail
                NewsDetailScreen(
                    articleId = detailScreen.articleId,
                    onBackClick = {
                        currentScreen = Screen.NewsList
                    }
                )
            }
        }
    }
}