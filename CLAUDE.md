# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## プロジェクト概要
ラリーガの最新ニュースを日本語で閲覧できるサッカーニュースアプリ
- Android/iOS両対応のネイティブアプリ
- シンプルで見やすいUI
- 動作確認レベルのサンプルアプリ

詳細な要件は[要件定義書](./docs/要件定義書.md)を参照

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

### 技術ルール
1. **コード品質**
   - Kotlin Multiplatformのベストプラクティスに従う
   - 共通ロジックはcommonMainに実装
   - UI部分もCompose Multiplatformで共通化

2. **UI/UX設計**
   - Material Design 3を基本とする
   - シンプルで直感的な操作
   - ローディング状態の表示
   - エラーハンドリング

3. **アーキテクチャ**
   - MVVM (Model-View-ViewModel) パターン
   - Repository パターンでデータ取得を抽象化
   - 依存性注入（DI）の準備

4. **テスト**
   - 動作確認レベルなので必須ではない
   - 主要な機能の手動テストシナリオを用意

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