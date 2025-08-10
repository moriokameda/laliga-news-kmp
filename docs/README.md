# ラリーガニュースアプリ ドキュメント

## 📁 ドキュメント構成

### 📋 [requirements/](./requirements/) - 要件関連
- [要件定義書](./requirements/要件定義書.md) - 機能要件、非機能要件、技術要件

### 🏗️ [architecture/](./architecture/) - 設計関連
- [技術設計書](./architecture/技術設計書.md) - システムアーキテクチャ、データ設計、パフォーマンス設計
- [画面設計書](./architecture/画面設計書.md) - UI/UXデザイン仕様、画面レイアウト

### 👨‍💻 [development/](./development/) - 開発者向け
- [コーディング規約](./development/コーディング規約.md) - **必読** Kotlin Multiplatform開発規約、DRY原則、ベストプラクティス
- [実装計画書](./development/実装計画書.md) - 段階的実装計画、チェックリスト
- [API統合ガイド](./development/API統合ガイド.md) - バックエンドAPI連携仕様
- [バックエンド構成と実装ガイド](./development/バックエンド構成と実装ガイド.md) - サーバーサイド実装ガイド

### 📈 [planning/](./planning/) - 計画関連
- [次のステップロードマップ](./planning/次のステップロードマップ.md) - 将来の拡張計画、技術ロードマップ

## 🚀 クイックスタート

### 開発を始める前に
1. **[CLAUDE.md](/CLAUDE.md)** を読んで開発プロセスを理解する
2. **[コーディング規約](./development/コーディング規約.md)** を読んでコードスタイルを把握する
3. **[要件定義書](./requirements/要件定義書.md)** でアプリの要件を確認する

### 実装時の参照順序
1. 機能要件の確認 → [要件定義書](./requirements/要件定義書.md)
2. アーキテクチャの理解 → [技術設計書](./architecture/技術設計書.md)
3. コーディング規約の確認 → [コーディング規約](./development/コーディング規約.md)
4. 画面実装時 → [画面設計書](./architecture/画面設計書.md)
5. API連携時 → [API統合ガイド](./development/API統合ガイド.md)

## 📝 重要な原則

### DRY原則（Don't Repeat Yourself）
- 同じコードを複数箇所に書かない
- 共通ロジックは`commonMain`に実装
- 詳細は[コーディング規約](./development/コーディング規約.md#基本原則)参照

### Kotlin Multiplatformベストプラクティス
- expect/actualパターンの適切な使用
- Coroutines + Flowによる非同期処理の統一
- 詳細は[CLAUDE.md](/CLAUDE.md#kotlin-multiplatformベストプラクティス)参照

## 🔗 関連リンク
- [プロジェクトルート README](/README.md)
- [CLAUDE.md（AI開発ガイド）](/CLAUDE.md)

---
最終更新: 2025年1月