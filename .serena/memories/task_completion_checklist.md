# タスク完了時のチェックリスト

## 必須確認項目

### 1. コード品質
- [ ] コードがKotlin Multiplatformのベストプラクティスに従っている
- [ ] 共通ロジックがcommonMainに適切に配置されている
- [ ] プラットフォーム固有のコードが適切に分離されている

### 2. ビルド確認
```bash
# Androidビルド確認
./gradlew :composeApp:assembleDebug

# iOSビルド確認（macOSの場合）
./gradlew :composeApp:iosSimulatorArm64MainBinaries
```

### 3. 手動テスト
- [ ] Android実機/エミュレータで動作確認
- [ ] iOS実機/シミュレータで動作確認（可能な場合）
- [ ] 主要な画面遷移の確認
- [ ] エラー状態の確認

### 4. リンティング（設定されている場合）
```bash
# Kotlin lint
./gradlew detekt

# コードフォーマット
./gradlew ktlintFormat
```

### 5. Git確認
```bash
# 変更内容の確認
git status
git diff

# 不要なファイルが含まれていないか確認
```

### 6. ドキュメント更新
- [ ] 必要に応じてREADME.mdを更新
- [ ] 新機能の場合は使用方法を記載
- [ ] CLAUDEからの指示がある場合は、CLAUDE.mdに追記

## 推奨事項
- コミットメッセージは変更内容を明確に記載
- プルリクエストには実装内容の詳細を記載
- スクリーンショットがあれば添付