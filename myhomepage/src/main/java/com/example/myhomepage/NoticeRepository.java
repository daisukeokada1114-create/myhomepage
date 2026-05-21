package com.example.myhomepage;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

// DB操作担当
public interface NoticeRepository extends JpaRepository<Notice, Long> {

  // お知らせを新しい順で取得するためのメソッド
  // spring Data JPA はメソッド名を読んで、自動でsqlを作ってくれる
  // findAllBy➡全件取得
  // OrderByIdDesc➡idの大きい順、つまり新しく登録された順
  List<Notice> findAllByOrderByIdDesc();
}
