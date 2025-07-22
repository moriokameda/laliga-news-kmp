import androidx.compose.runtime.Composable
import presentation.screen.NewsListScreen
import theme.LaLigaNewsTheme

@Composable
fun App() {
    LaLigaNewsTheme {
        NewsListScreen()
    }
}