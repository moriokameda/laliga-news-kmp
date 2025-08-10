# 推奨コマンド一覧

## ビルドコマンド
```bash
# 全ターゲットビルド
./gradlew build

# Androidデバッグビルド
./gradlew :composeApp:assembleDebug

# Androidリリースビルド
./gradlew :composeApp:assembleRelease

# Android App Bundle (AAB) ビルド
./gradlew :composeApp:bundleRelease

# iOSビルド (macOS必須)
./gradlew :composeApp:iosSimulatorArm64MainBinaries

# クリーンビルド
./gradlew clean
```

## 実行コマンド
```bash
# Androidアプリ実行
./gradlew :composeApp:installDebug

# iOSシミュレータ実行 (macOS必須)
./gradlew :composeApp:iosSimulatorArm64MainBinaries
```

## テストコマンド
```bash
# 全テスト実行
./gradlew test

# Androidテスト
./gradlew :composeApp:testDebugUnitTest

# iOSテスト
./gradlew :composeApp:iosSimulatorArm64Test
```

## コード品質
```bash
# Kotlin linting (detektが設定されている場合)
./gradlew detekt

# コードフォーマット (ktlintが設定されている場合)
./gradlew ktlintFormat
```

## Git関連
```bash
# ステータス確認
git status

# 変更確認
git diff

# コミット履歴
git log --oneline -10

# ブランチ作成
git checkout -b feature/branch-name

# プルリクエスト作成 (GitHub CLI)
gh pr create
```

## リリース準備
```bash
# キーストア生成スクリプト実行
./generate-keystore.sh

# GitHub Actionsでリリースビルド（タグプッシュ時）
git tag v1.0.0
git push origin v1.0.0
```

## システムコマンド (macOS/Darwin)
```bash
# ファイル検索
find . -name "*.kt"

# テキスト検索
grep -r "NewsArticle" --include="*.kt"

# ディレクトリ作成
mkdir -p path/to/directory

# ファイル権限変更
chmod +x script.sh
```