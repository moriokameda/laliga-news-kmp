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
import presentation.component.NewsItemCard
import presentation.viewmodel.NewsListViewModel
import theme.LaLigaRed
import theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsListScreen() {
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
            state.isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = LaLigaRed)
                }
            }
            
            state.error != null -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "エラーが発生しました",
                            fontSize = 16.sp,
                            color = Color.Red
                        )
                        Text(
                            text = state.error,
                            fontSize = 14.sp,
                            color = Color.Gray
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = { viewModel.refresh() },
                            colors = ButtonDefaults.buttonColors(containerColor = LaLigaRed)
                        ) {
                            Text("再試行", color = White)
                        }
                    }
                }
            }
            
            state.articles.isEmpty() -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "ニュースがありません",
                        fontSize = 16.sp,
                        color = Color.Gray
                    )
                }
            }
            
            else -> {
                LazyColumn {
                    items(state.articles) { article ->
                        NewsItemCard(
                            article = article,
                            onClick = {
                                // TODO: 詳細画面への遷移（段階3で実装）
                            }
                        )
                    }
                }
            }
        }
    }
}