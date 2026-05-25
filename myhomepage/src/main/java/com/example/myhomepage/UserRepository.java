package com.example.myhomepage;

import org.springframework.data.jpa.repository.JpaRepository;

// users テーブルを操作する Repository 
// Repository はDB操作担当
public interface UserRepository extends JpaRepository<User, Long> {

  // username と password が一致するユーザーを探す
  // ログイン時に使う
  //
  // 一致するデータがあれば user が返る
  // 一致しなければ null が返る

  User findByUsernameAndPassword(String username, String password);

}
