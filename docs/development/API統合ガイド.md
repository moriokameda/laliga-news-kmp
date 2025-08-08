# ラリーガニュースアプリ API統合ガイド

## 1. 概要

このドキュメントでは、Kotlin Multiplatformアプリから**Ktorバックエンド**を経由して外部APIと連携する方法を説明します。

### 選定技術スタック
- **バックエンド**: Ktor（Kotlin製の軽量Webフレームワーク）
- **翻訳API**: Microsoft Translator（月200万文字まで無料）
- **データベース**: Supabase（PostgreSQL + リアルタイム機能）

## 2. アーキテクチャ概要

```
┌─────────────────┐     ┌─────────────────┐     ┌─────────────────┐
│ KMP Mobile App  │────▶│  Ktor Backend   │────▶│  External APIs  │
│  (iOS/Android)  │◀────│   (REST API)    │◀────│  & Database     │
└─────────────────┘     └─────────────────┘     └─────────────────┘
```

## 3. Kotlin Multiplatform側の実装

### 3.1 依存関係追加
```kotlin
// composeApp/build.gradle.kts
kotlin {
    sourceSets {
        commonMain.dependencies {
            // Ktor Client（アプリ側）
            implementation("io.ktor:ktor-client-core:2.3.12")
            implementation("io.ktor:ktor-client-content-negotiation:2.3.12")
            implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.12")
            
            // その他
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1")
            implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.6.0")
        }
        
        androidMain.dependencies {
            implementation("io.ktor:ktor-client-android:2.3.12")
        }
        
        iosMain.dependencies {
            implementation("io.ktor:ktor-client-darwin:2.3.12")
        }
    }
}
```

### 3.2 API Client実装
```kotlin
// data/remote/LaLigaNewsApiClient.kt
class LaLigaNewsApiClient(
    private val baseUrl: String = BuildConfig.API_BASE_URL // "https://api.laliga-news.com"
) {
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
            })
        }
        
        install(HttpTimeout) {
            requestTimeoutMillis = 15000
        }
        
        install(Logging) {
            level = LogLevel.INFO
        }
        
        // 共通ヘッダー
        defaultRequest {
            header("Accept", "application/json")
            header("X-Client-Version", "1.0.0")
            header("X-Platform", getPlatformName())
        }
    }
    
    // ニュース一覧取得
    suspend fun getNewsList(
        page: Int = 1,
        limit: Int = 20,
        language: String = "ja"
    ): Result<NewsListResponse> = runCatching {
        client.get("$baseUrl/api/news") {
            parameter("page", page)
            parameter("limit", limit)
            parameter("lang", language)
        }.body()
    }
    
    // ニュース詳細取得
    suspend fun getNewsDetail(
        newsId: String
    ): Result<NewsDetailResponse> = runCatching {
        client.get("$baseUrl/api/news/$newsId").body()
    }
    
    // ニュース更新（プルリフレッシュ）
    suspend fun refreshNews(): Result<NewsListResponse> = runCatching {
        client.post("$baseUrl/api/news/refresh").body()
    }
}

// プラットフォーム判定
expect fun getPlatformName(): String

// androidMain
actual fun getPlatformName(): String = "Android"

// iosMain
actual fun getPlatformName(): String = "iOS"
```

### 3.3 Repository実装（キャッシュ付き）
```kotlin
// data/repository/NewsRepositoryImpl.kt
class NewsRepositoryImpl(
    private val apiClient: LaLigaNewsApiClient,
    private val database: AppDatabase,
    private val settings: Settings
) : NewsRepository {
    
    override fun getNewsList(): Flow<List<NewsArticle>> = flow {
        // 1. ローカルDBから即座に表示
        val cachedNews = database.newsDao().getAllNews()
        emit(cachedNews.map { it.toNewsArticle() })
        
        // 2. APIから最新データ取得
        apiClient.getNewsList().fold(
            onSuccess = { response ->
                val newsArticles = response.articles.map { it.toNewsArticle() }
                
                // 3. DBを更新
                database.newsDao().deleteAll()
                database.newsDao().insertAll(newsArticles.map { it.toEntity() })
                
                // 4. 最新データを配信
                emit(newsArticles)
                
                // 5. 最終更新時刻を保存
                settings.putLong("last_update", Clock.System.now().toEpochMilliseconds())
            },
            onFailure = { error ->
                // エラー時はキャッシュデータをそのまま使用
                if (cachedNews.isEmpty()) {
                    throw error
                }
            }
        )
    }
    
    override suspend fun refreshNews() {
        apiClient.refreshNews().fold(
            onSuccess = { response ->
                val newsArticles = response.articles.map { it.toNewsArticle() }
                database.newsDao().deleteAll()
                database.newsDao().insertAll(newsArticles.map { it.toEntity() })
            },
            onFailure = { error ->
                throw error
            }
        )
    }
}
```

## 4. Ktorバックエンド側のAPI仕様

### 4.1 エンドポイント一覧

| エンドポイント | メソッド | 説明 | パラメータ |
|-------------|---------|------|-----------|
| `/api/news` | GET | ニュース一覧取得 | page, limit, lang |
| `/api/news/:id` | GET | ニュース詳細取得 | - |
| `/api/news/refresh` | POST | 最新ニュース取得 | - |
| `/api/favorites` | GET | お気に入り一覧 | user_id |
| `/api/favorites` | POST | お気に入り追加 | news_id, user_id |
| `/api/favorites/:id` | DELETE | お気に入り削除 | - |

### 4.2 レスポンス形式

#### 成功時
```json
{
  "status": "success",
  "data": {
    "articles": [
      {
        "id": "123",
        "title": "レアル・マドリード、エル・クラシコで圧勝",
        "summary": "サンティアゴ・ベルナベウでの一戦で...",
        "content": "詳細な記事内容...",
        "imageUrls": ["https://..."],
        "publishDateTime": "2025-07-31T10:00:00Z",
        "source": "ESPN",
        "originalLanguage": "es",
        "isTranslated": true
      }
    ],
    "pagination": {
      "page": 1,
      "limit": 20,
      "total": 150,
      "hasNext": true
    }
  }
}
```

#### エラー時
```json
{
  "status": "error",
  "error": {
    "code": "RATE_LIMIT_EXCEEDED",
    "message": "APIレート制限を超えました",
    "retryAfter": 3600
  }
}
```

## 5. 実装上の注意点

### 5.1 ネットワークエラーハンドリング
```kotlin
sealed class NetworkError : Exception() {
    object NoInternet : NetworkError()
    object ServerError : NetworkError()
    object Timeout : NetworkError()
    data class HttpError(val code: Int, val message: String) : NetworkError()
}

suspend fun <T> safeApiCall(
    apiCall: suspend () -> T
): Result<T> = try {
    Result.success(apiCall())
} catch (e: Exception) {
    when (e) {
        is UnresolvedAddressException -> Result.failure(NetworkError.NoInternet)
        is SocketTimeoutException -> Result.failure(NetworkError.Timeout)
        is HttpException -> {
            Result.failure(NetworkError.HttpError(e.code, e.message))
        }
        else -> Result.failure(e)
    }
}
```

### 5.2 画像キャッシュ戦略
```kotlin
// Coil for Compose Multiplatform
AsyncImage(
    model = ImageRequest.Builder(LocalContext.current)
        .data(article.imageUrl)
        .crossfade(true)
        .memoryCacheKey(article.id) // メモリキャッシュキー
        .diskCacheKey(article.id)   // ディスクキャッシュキー
        .build(),
    contentDescription = article.title,
    modifier = Modifier.fillMaxSize(),
    error = painterResource(Res.drawable.error_placeholder)
)
```

### 5.3 プラットフォーム別の設定

#### Android
```kotlin
// androidMain/kotlin/PlatformConfig.kt
actual class PlatformConfig {
    actual fun getApiBaseUrl(): String {
        return if (BuildConfig.DEBUG) {
            "http://10.0.2.2:8080" // Android Emulator用
        } else {
            "https://api.laliga-news.com"
        }
    }
}
```

#### iOS
```kotlin
// iosMain/kotlin/PlatformConfig.kt
actual class PlatformConfig {
    actual fun getApiBaseUrl(): String {
        return if (Platform.isDebugBinary) {
            "http://localhost:8080"
        } else {
            "https://api.laliga-news.com"
        }
    }
}
```

## 6. セキュリティ考慮事項

### 6.1 APIキーの管理
```kotlin
// BuildConfigの使用（Android）
android {
    buildTypes {
        debug {
            buildConfigField("String", "API_KEY", "\"${getLocalProperty("api.key.debug")}\"")
        }
        release {
            buildConfigField("String", "API_KEY", "\"${getEnvironmentVariable("API_KEY")}\"")
        }
    }
}

// iOS Info.plist
<key>API_KEY</key>
<string>$(API_KEY)</string>
```

### 6.2 証明書ピンニング（オプション）
```kotlin
// Ktorクライアントでの証明書ピンニング
val client = HttpClient {
    engine {
        // Android
        sslManager = { httpsURLConnection ->
            httpsURLConnection.sslSocketFactory = getPinnedCertificateSocketFactory()
        }
    }
}
```

## 7. パフォーマンス最適化

### 7.1 ページネーション
```kotlin
class PagingNewsRepository(
    private val apiClient: LaLigaNewsApiClient
) {
    private var currentPage = 1
    private var hasMorePages = true
    
    suspend fun loadNextPage(): List<NewsArticle> {
        if (!hasMorePages) return emptyList()
        
        return apiClient.getNewsList(page = currentPage).fold(
            onSuccess = { response ->
                currentPage++
                hasMorePages = response.pagination.hasNext
                response.articles.map { it.toNewsArticle() }
            },
            onFailure = { 
                hasMorePages = false
                emptyList()
            }
        )
    }
}
```

### 7.2 プリフェッチ戦略
```kotlin
// 次のページを事前に取得
LaunchedEffect(listState) {
    snapshotFlow { listState.layoutInfo.visibleItemsInfo }
        .collect { visibleItems ->
            val lastVisibleItem = visibleItems.lastOrNull()
            val totalItems = listState.layoutInfo.totalItemsCount
            
            // 最後から3つ目のアイテムが表示されたら次ページを取得
            if (lastVisibleItem != null && 
                lastVisibleItem.index >= totalItems - 3) {
                viewModel.loadNextPage()
            }
        }
}
```

## 8. テスト戦略

### 8.1 Mock API Client
```kotlin
class MockLaLigaNewsApiClient : LaLigaNewsApiClient {
    override suspend fun getNewsList(
        page: Int,
        limit: Int,
        language: String
    ): Result<NewsListResponse> {
        // テスト用のダミーデータを返す
        return Result.success(
            NewsListResponse(
                articles = listOf(
                    NewsDto(
                        id = "test-1",
                        title = "テスト記事",
                        // ...
                    )
                ),
                pagination = PaginationDto(
                    page = page,
                    limit = limit,
                    total = 100,
                    hasNext = true
                )
            )
        )
    }
}
```

### 8.2 統合テスト
```kotlin
@Test
fun testNewsListIntegration() = runTest {
    val repository = NewsRepositoryImpl(
        apiClient = MockLaLigaNewsApiClient(),
        database = createInMemoryDatabase(),
        settings = MockSettings()
    )
    
    repository.getNewsList().test {
        // 最初のemit（キャッシュ）
        val cached = awaitItem()
        assertTrue(cached.isEmpty())
        
        // 2回目のemit（API取得後）
        val fromApi = awaitItem()
        assertTrue(fromApi.isNotEmpty())
        assertEquals("テスト記事", fromApi.first().title)
    }
}
```

## 9. トラブルシューティング

### よくある問題と解決法

1. **iOS Simulatorでlocalhostに接続できない**
   - `localhost`の代わりにMacのIPアドレスを使用
   - Info.plistでApp Transport Securityを設定

2. **Androidエミュレータでのネットワークエラー**
   - `10.0.2.2`を使用してホストマシンに接続
   - インターネット権限の確認

3. **KtorクライアントのJSON解析エラー**
   - `ignoreUnknownKeys = true`を設定
   - `isLenient = true`で柔軟な解析を有効化

4. **画像が表示されない**
   - HTTPSを使用しているか確認
   - 画像URLが正しいか検証
   - キャッシュをクリアして再試行

---
作成日: 2025-07-31
バージョン: 1.0