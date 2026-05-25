package com.example.myhomepage;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// このクラスをDBテーブルとして扱う
@Entity

// user という名前はDBで予約語になることがあるため
// テーブル名は users にしておく
@Table(name = "users")

public class User {

  // 主キー
  // DBの1件１件を区別するための番号
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // ログインIDとして使う名前
  private String username;

  // ログイン時に確認するパスワード
  // 今回は、学習用なので平文で保存、 実務では、必ず暗号化する
  private String password;

  // IDを取得する
  public Long getId() {
    return id;
  }

  // Idを設定する
  public void setId(Long id) {
    this.id = id;
  }

  // username を取得する
  public String getUsername() {
    return username;
  }

  // username を設定する
  public void setUsername(String username) {
    this.username = username;
  }

  // password を取得する
  public String getPassword() {
    return password;
  }

  // password を設定する
  public void setPassword(String password) {
    this.password = password;
  }

}
