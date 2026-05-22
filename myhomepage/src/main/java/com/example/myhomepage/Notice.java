package com.example.myhomepage;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// このクラスをDBテーブルとして扱う
@Entity
public class Notice {

  // 主キー
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // お知らせタイトル
  private String title;

  // お知らせ本文
  private String content;

  // 作成日時
  // いつ作られたデータかを保存をする
  // LocalDateTimeは日付＋時間を扱うクラス
  private LocalDateTime createdAt;

  // 作成日時取得
  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  // 作成日時保存
  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  // 更新日時
  private LocalDateTime updatedAt;

  // 更新日時を取得
  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  // 更新日時設定
  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  // ID取得
  public Long getId() {
    return id;
  }

  // ID設定
  public void setId(Long id) {
    this.id = id;
  }

  // タイトル取得
  public String getTitle() {
    return title;
  }

  // タイトル設定
  public void setTitle(String title) {
    this.title = title;
  }

  // 本文取得
  public String getContent() {
    return content;
  }

  // 本文設定
  public void setContent(String content) {
    this.content = content;
  }
}