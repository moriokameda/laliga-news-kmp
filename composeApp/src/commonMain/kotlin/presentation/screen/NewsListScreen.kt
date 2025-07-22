package presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import theme.LaLigaRed
import theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsListScreen() {
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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "ニュース一覧\n（サンプルデータは次の段階で追加）",
                fontSize = 16.sp,
                color = Color.Gray
            )
        }
    }
}