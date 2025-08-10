# ラリーガニュースアプリ プロジェクト概要

## プロジェクトの目的
ラリーガ（スペインサッカーリーグ）の最新ニュースを日本語で閲覧できるサッカーニュースアプリの開発

## 主要機能
1. **ニュース一覧画面** - ラリーガのニュース記事をリスト形式で表示
2. **ニュース詳細画面** - 記事の詳細内容を表示
3. **データ取得** - 初期はサンプルデータ、将来的に実APIと統合

## 技術スタック
- **Kotlin Multiplatform** - Android/iOS共通のビジネスロジック
- **Compose Multiplatform** - 共通UI実装
- **Gradle** - ビルドシステム（Kotlin DSL）
- **JDK 21** - Javaバージョン
- **Kotlin 2.0.0** - Kotlinバージョン
- **Compose Plugin 1.6.11** - Compose Multiplatformバージョン

## アーキテクチャ
- **MVVM** (Model-View-ViewModel) パターン
- **Repository** パターンでデータ取得を抽象化
- **Material Design 3** ベースのUI/UX

## プラットフォーム要件
- Android: minSdk 24, targetSdk 34
- iOS: Compose Multiplatform iOS対応（macOS + Xcode必須）

## 現在の状況
- 段階1-5の実装完了（サンプルアプリレベル）
- Android/iOS両プラットフォームで動作確認済み
- リリース準備とAPI統合が次のステップ