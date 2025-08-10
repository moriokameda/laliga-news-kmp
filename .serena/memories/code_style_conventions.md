# コードスタイルと規約

## Kotlinコーディング規約
- Kotlin公式コーディング規約に準拠
- クラス名: PascalCase (例: NewsArticle, NewsListViewModel)
- 関数名: camelCase (例: getNewsList, loadMoreArticles)
- 定数: UPPER_SNAKE_CASE (例: MAX_RETRY_COUNT)
- パッケージ名: 小文字 (例: com.laliga.news.data.model)

## プロジェクト構造
```
composeApp/
├── src/
│   ├── commonMain/        # 共通コード
│   │   └── kotlin/
│   │       ├── data/      # データ層
│   │       │   ├── model/
│   │       │   └── repository/
│   │       ├── presentation/  # プレゼンテーション層
│   │       │   ├── component/
│   │       │   ├── screen/
│   │       │   ├── state/
│   │       │   └── viewmodel/
│   │       └── theme/     # テーマ定義
│   ├── androidMain/       # Android固有コード
│   └── iosMain/          # iOS固有コード
```

## 命名規約
- **データモデル**: NewsArticle, Team など（単数形）
- **リポジトリ**: NewsRepository, FavoritesRepository（インターフェース）
- **ViewModels**: NewsListViewModel, NewsDetailViewModel
- **画面**: NewsListScreen, NewsDetailScreen
- **コンポーネント**: NewsItemCard, EmptyStateView, ErrorView

## Compose規約
- Composable関数は大文字で開始
- Modifierパラメータは最初に配置
- StateはViewModelで管理
- 副作用はLaunchedEffectやrememberCoroutineScopeで処理

## その他の規約
- エラーハンドリングは必須
- ローディング状態の表示
- 空状態の適切な表示
- Material Design 3ガイドラインの遵守