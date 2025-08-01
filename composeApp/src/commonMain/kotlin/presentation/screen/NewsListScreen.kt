package presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import presentation.component.AnimatedNewsCard
import presentation.component.NetworkErrorView
import presentation.component.EmptyStateView
import presentation.component.PullToRefreshBox
import presentation.component.NewsListSkeleton
import presentation.viewmodel.NewsListViewModel
import theme.LaLigaRed
import theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsListScreen(
    onNewsClick: (String) -> Unit = {}
) {
    val viewModel = remember { NewsListViewModel() }
    val state = viewModel.state
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        // ヘッダー
        TopAppBar(
            title = {
                Text(
                    text = "ラリーガニュース",
                    color = White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = LaLigaRed
            ),
            modifier = Modifier.height(56.dp)
        )
        
        // メインコンテンツエリア
        when {
            state.error != null -> {
                NetworkErrorView(
                    onRetry = { viewModel.refresh() }
                )
            }
            
            state.isLoading && state.articles.isEmpty() -> {
                // 初回ローディング時はスケルトンUI
                NewsListSkeleton()
            }
            
            state.articles.isEmpty() && !state.isLoading -> {
                EmptyStateView()
            }
            
            else -> {
                PullToRefreshBox(
                    isRefreshing = state.isLoading && state.articles.isNotEmpty(),
                    onRefresh = { viewModel.refresh() },
                    modifier = Modifier.fillMaxSize()
                ) {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(
                            items = state.articles,
                            key = { article -> article.id }
                        ) { article ->
                            AnimatedNewsCard(
                                article = article,
                                onClick = {
                                    onNewsClick(article.id)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}