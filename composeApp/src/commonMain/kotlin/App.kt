import androidx.compose.animation.*
import androidx.compose.animation.core.tween
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
        AnimatedContent(
            targetState = currentScreen,
            transitionSpec = {
                if (targetState is Screen.NewsDetail) {
                    // 詳細画面へ遷移（右からスライドイン）
                    slideInHorizontally(
                        initialOffsetX = { width -> width },
                        animationSpec = tween(300)
                    ) togetherWith slideOutHorizontally(
                        targetOffsetX = { width -> -width / 3 },
                        animationSpec = tween(300)
                    )
                } else {
                    // 一覧画面へ戻る（左からスライドイン）
                    slideInHorizontally(
                        initialOffsetX = { width -> -width / 3 },
                        animationSpec = tween(300)
                    ) togetherWith slideOutHorizontally(
                        targetOffsetX = { width -> width },
                        animationSpec = tween(300)
                    )
                }
            }
        ) { screen ->
            when (screen) {
                is Screen.NewsList -> {
                    NewsListScreen(
                        onNewsClick = { articleId ->
                            currentScreen = Screen.NewsDetail(articleId)
                        }
                    )
                }
                
                is Screen.NewsDetail -> {
                    NewsDetailScreen(
                        articleId = screen.articleId,
                        onBackClick = {
                            currentScreen = Screen.NewsList
                        }
                    )
                }
            }
        }
    }
}