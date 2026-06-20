# 学生管理システム（OOP応用版）

![Java](https://img.shields.io/badge/Java-17+-blue)
![OOP](https://img.shields.io/badge/OOP-応用-green)
![Week](https://img.shields.io/badge/Week-5完成-brightgreen)
![License](https://img.shields.io/badge/License-MIT-yellow)
![Lines](https://img.shields.io/badge/Lines-600~700-orange)

**Week 5: Java応用・オブジェクト指向応用 完成版**

継承・ポリモーフィズム・抽象化・例外処理・Lombokを統合した
エンタープライズレベル学生管理システム

## 📋 目次
- [概要](#概要)
- [技術スタック](#技術スタック)
- [主要機能](#主要機能)
- [OOP設計](#oop設計)
- [セットアップ](#セットアップ)
- [使用方法](#使用方法)
- [学習成果](#学習成果)
- [今後の拡張予定](#今後の拡張予定)
- [ライセンス](#ライセンス)

## 🎯 概要

このプロジェクトは、Java学習プログラムWeek 5の集大成として開発された、
オブジェクト指向プログラミングの高度な概念を統合した学生管理システムです。

### ✨ 特徴
- **継承階層**: Person → Student の明確な継承関係
- **ポリモーフィズム**: 統一インターフェースによる異なる学生タイプの処理
- **抽象化**: インターフェース・抽象クラスによる設計品質確保
- **例外処理**: カスタム例外による堅牢なエラーハンドリング
- **Lombok統合**: 86%のコード削減による効率的な開発

## 🛠️ 技術スタック

| 技術 | バージョン | 用途 |
|------|------------|------|
| Java | 17+ | 主要開発言語 |
| Lombok | Latest | コード生成・効率化 |
| Maven | 3.6+ | 依存関係管理 |
| JUnit | 5+ | 単体テスト（拡張予定） |

### 📦 依存関係
```xml
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.28</version>
</dependency>

## 🏗️ システム構成

### クラス構成（600-700行規模）
src/
├── Person.java              # 基底クラス（継承の親）
├── Student.java             # 学生クラス（Person継承）
├── StudentManager.java      # 学生管理クラス
├── Grade.java              # 成績クラス
├── GradeCalculator.java    # 成績計算インターフェース
├── ReportGenerator.java    # レポート生成インターフェース
├── DataValidator.java      # データ検証インターフェース
├── CustomExceptions.java   # カスタム例外（4種類）
└── Main.java              # メインクラス

## 🌟 追加機能アイデア

### 短期目標
- [ ] CSVインポート・エクスポート機能
- [ ] 成績グラフ表示機能
- [ ] 学生写真管理機能

### 中期目標
- [ ] Web化（Spring Boot + Thymeleaf）
- [ ] データベース永続化（MySQL）
- [ ] ユーザー認証機能

### 長期目標
- [ ] モバイルアプリ化
- [ ] クラウドデプロイ（AWS）
- [ ] AI成績予測機能

## 💡 機能概要
学生データをJSON形式でエクスポートする機能を追加

## 🎯 要件
- 全学生データをJSON形式で出力
- 1件単位・全件一括の両方に対応
- ファイル名は自動生成（students_YYYYMMDD_HHMMSS.json）

## 📋 実装
1. `StudentManager.java` に `exportToJSON()` メソッド追加
2. メニューに新しい選択肢を追加
3. JSON形式の妥当性検証

## ✅ 完了
- JSONファイルが正しく生成される
- ファイルが有効なJSON形式である
- エラーハンドリングが適切に実装されている

## 🌿 ブランチ戦略

このプロジェクトでは**Feature Branch Workflow**を採用しています。

### ブランチ構成

- **`main`**: 安定版ブランチ。常にデプロイ可能な状態を維持
- **`feature/*`**: 機能開発ブランチ。Issue番号と機能名で命名
  - 例: `feature/issue-1-fix-deletion-confirmation`
  - 例: `feature/issue-2-add-csv-export`

### 開発フロー

1. **Issue作成**: GitHub Issuesで作業内容を明確化
2. **ブランチ作成**: `git switch -c feature/issue-X-description`
3. **機能開発**: 独立した環境で安全に開発
4. **コミット**: 明確なコミットメッセージで記録
5. **マージ**: mainブランチに統合後、フィーチャーブランチを削除

### コミットメッセージ規約

- **Fix #X**: バグ修正
- **Add #X**: 新機能追加
- **Update #X**: 既存機能改善
- **Remove #X**: 機能削除

例:
```bash
git commit -m ""Fix #1: 削除確認機能の入力判定を改善

- 大文字小文字を区別しない判定に変更
- y/yes/はい/削除 の複数パターンに対応""

**最終更新**: GitHub Web編集にて追加
