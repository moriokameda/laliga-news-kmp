# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## プロジェクト概要
ラリーガの最新ニュースを日本語で閲覧できるサッカーニュースアプリ
- Android/iOS両対応のネイティブアプリ
- シンプルで見やすいUI
- 動作確認レベルのサンプルアプリ

詳細な要件は[要件定義書](./docs/requirements/要件定義書.md)を参照

## アプリの主要機能
1. **ニュース一覧画面**（最優先）
   - ラリーガのニュース記事をリスト形式で表示
   - 各記事にはタイトル、要約、画像を表示
   - スクロール可能なリスト

2. **ニュース詳細画面**
   - 一覧からタップした記事の詳細を表示
   - タイトル、本文、画像を表示
   - 戻るボタンで一覧に戻る

3. **データ取得**
   - インターネットから最新ニュースを取得
   - 初期実装ではサンプルデータを使用（動作確認用）
   - 将来的に実際のAPIに切り替え可能な設計

## 開発ルール

### 実装プロセス（必須）
新機能の実装や変更を行う際は、以下のプロセスを必ず守ること：

1. **事前調査フェーズ**
   - 現状のソースコードを探索し、既存の実装を理解する
   - 関連する技術やライブラリについてWebで調査する
   - 類似機能の実装例やベストプラクティスを確認する

2. **要件確認フェーズ**
   - 調査結果を踏まえて、実装する機能の要件を明確にする
   - 不明点や技術的な選択肢がある場合は、ユーザーに確認する
   - 要件の優先順位を整理する

3. **実装計画フェーズ**
   - 詳細な実装プランを作成する
   - 必要なファイルの作成・変更リストを明示する
   - 実装の順序とステップを明確にする
   - **必ずユーザーにレビューしてもらい、承認を得る**

4. **実装フェーズ**
   - レビュー承認後に実際のコーディングを開始する
   - 段階的に実装し、各段階で動作確認可能にする
   - [コーディング規約](./docs/development/コーディング規約.md)に従って実装する

### Kotlin Multiplatformベストプラクティス

#### DRY原則の実践
- **共通コードの最大活用**: ビジネスロジック、データモデル、ViewModelはcommonMainに実装
- **拡張関数の活用**: 共通処理は拡張関数として定義し、再利用性を高める
- **expect/actualの適切な使用**: プラットフォーム固有の実装が必要な場合のみ使用

```kotlin
// commonMain - 共通ロジック
fun List<NewsArticle>.filterByDate(date: LocalDate): List<NewsArticle> {
    return filter { it.publishedAt.toLocalDate() == date }
}

// プラットフォーム固有の実装はexpect/actualで
expect fun getPlatformName(): String
```

#### アーキテクチャパターン
- **MVVM**: View(Compose) → ViewModel → Repository → DataSource
- **単方向データフロー**: UI Event → ViewModel → State → UI
- **Repository パターン**: データソースの抽象化と切り替え可能な設計

#### 非同期処理の統一
- **Coroutines + Flow**: すべての非同期処理で使用
- **viewModelScope**: ViewModelでの非同期処理に必須
- **構造化された並行処理**: 適切なキャンセレーション処理

```kotlin
viewModelScope.launch {
    repository.getNews()
        .onSuccess { /* 成功処理 */ }
        .onFailure { /* エラー処理 */ }
}
```

#### 依存性注入
- **Koin推奨**: 小〜中規模プロジェクトに最適
- **モジュール定義**: commonMainで定義、各プラットフォームで初期化

### コード品質基準
1. **再利用性（DRY）**: 重複コードを排除し、共通化を徹底
2. **プラットフォーム間の一貫性**: 同じ動作を保証
3. **パフォーマンス最適化**: LazyColumn、remember、キャッシュ活用
4. **保守性・可読性**: 明確な命名、適切なコメント
5. **拡張性**: 将来の機能追加を考慮した設計

詳細は[コーディング規約](./docs/development/コーディング規約.md)を参照

## Project Overview
This is a Kotlin Multiplatform project using Compose Multiplatform for both Android and iOS targets. The project was generated using the Kotlin Multiplatform Template (KMT) and supports:
- Android with Compose UI
- iOS with Compose UI

## Common Development Commands

### Build Commands
```bash
# Build all targets
./gradlew build

# Build Android app
./gradlew :composeApp:assembleDebug

# Build iOS app (requires macOS)
./gradlew :composeApp:iosSimulatorArm64MainBinaries

# Clean build
./gradlew clean
```

### Running Applications
```bash
# Run Android app
./gradlew :composeApp:installDebug

# Run iOS simulator (requires macOS and Xcode)
./gradlew :composeApp:iosSimulatorArm64MainBinaries
```

### Testing Commands
```bash
# Run all tests
./gradlew test

# Run common tests
./gradlew :composeApp:testDebugUnitTest

# Run Android tests
./gradlew :composeApp:testDebugUnitTest

# Run iOS tests
./gradlew :composeApp:iosSimulatorArm64Test
```

### Lint and Code Quality
```bash
# Run Kotlin linting
./gradlew detekt

# Format code
./gradlew ktlintFormat
```

## Architecture Overview

### Project Structure
- `composeApp/` - Main multiplatform module containing shared code
  - `src/commonMain/` - Shared code for all platforms
  - `src/androidMain/` - Android-specific code
  - `src/iosMain/` - iOS-specific code
  - `src/commonTest/` - Shared test code

### Key Technologies
- **Kotlin Multiplatform**: Shared business logic across platforms
- **Compose Multiplatform**: Shared UI components and screens
- **Gradle**: Build system with Kotlin DSL
- **JDK 21**: Target Java version

### Development Environment
- IntelliJ IDEA/Android Studio for development
- Xcode required for iOS development (macOS only)
- JetBrains Fleet configuration available

## Platform-Specific Notes

### Android
- Minimum SDK and target SDK versions defined in build.gradle
- Uses Compose for UI with Material Design components
- Standard Android development practices apply

### iOS
- Requires macOS for development and testing
- Uses Compose Multiplatform's iOS support
- Xcode integration for iOS-specific features

## Dependencies and Configuration
- Project uses Gradle version catalog for dependency management
- Multiplatform dependencies should be added to commonMain
- Platform-specific dependencies go in respective platform modules