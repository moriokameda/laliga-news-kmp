package presentation.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import data.model.NewsArticle
import data.repository.SampleNewsRepository
import theme.LaLigaRed
import theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsDetailScreen(
    articleId: String,
    onBackClick: () -> Unit
) {
    val repository = remember { SampleNewsRepository() }
    var article by remember { mutableStateOf<NewsArticle?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    
    LaunchedEffect(articleId) {
        try {
            val articles = repository.getNewsList()
            article = articles.find { it.id == articleId }
        } catch (e: Exception) {
            // エラーハンドリング
        } finally {
            isLoading = false
        }
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        // ヘッダー
        TopAppBar(
            title = {
                Text(
                    text = article?.title?.take(20) ?: "記事詳細",
                    color = White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )
            },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "戻る",
                        tint = White
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = LaLigaRed
            )
        )
        
        when {
            isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = LaLigaRed)
                }
            }
            
            article == null -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "記事が見つかりません",
                            fontSize = 16.sp,
                            color = Color.Red
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = onBackClick,
                            colors = ButtonDefaults.buttonColors(containerColor = LaLigaRed)
                        ) {
                            Text("戻る", color = White)
                        }
                    }
                }
            }
            
            else -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    // 記事タイトル
                    Text(
                        text = article!!.title,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.padding(16.dp)
                    )
                    
                    // 画像スライドショー
                    if (article!!.imageUrls.isNotEmpty()) {
                        ImageSlideshow(
                            imageUrls = article!!.imageUrls,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                        )
                    }
                    
                    // メタデータ
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "公開日時: ${article!!.publishDateTime}",
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "ソース: ${article!!.source}",
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                        if (article!!.isTranslated) {
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "※ ${article!!.originalLanguage}から翻訳されました",
                                fontSize = 12.sp,
                                color = Color.Blue,
                                fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
                            )
                        }
                    }
                    
                    Divider(
                        color = Color.LightGray,
                        thickness = 1.dp,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    
                    // 記事本文
                    Text(
                        text = article!!.content,
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageSlideshow(
    imageUrls: List<String>,
    modifier: Modifier = Modifier
) {
    if (imageUrls.size == 1) {
        // 画像が1枚の場合は単純表示
        Box(
            modifier = modifier
                .padding(horizontal = 16.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            Card(
                modifier = Modifier.fillMaxSize(),
                colors = CardDefaults.cardColors(containerColor = Color.LightGray)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "⚽",
                        fontSize = 48.sp
                    )
                }
            }
        }
    } else {
        // 複数画像の場合はスライドショー
        val pagerState = rememberPagerState(pageCount = { imageUrls.size })
        
        Column(modifier = modifier) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = 16.dp)
            ) { page ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(8.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Card(
                        modifier = Modifier.fillMaxSize(),
                        colors = CardDefaults.cardColors(containerColor = Color.LightGray)
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "⚽",
                                fontSize = 48.sp
                            )
                        }
                    }
                }
            }
            
            // ページインジケーター
            if (imageUrls.size > 1) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    repeat(imageUrls.size) { index ->
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .clip(RoundedCornerShape(4.dp))
                                .background(
                                    if (index == pagerState.currentPage) LaLigaRed
                                    else Color.LightGray
                                )
                        )
                        if (index < imageUrls.size - 1) {
                            Spacer(modifier = Modifier.width(4.dp))
                        }
                    }
                }
            }
        }
    }
}