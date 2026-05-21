package com.example.myhomepage;

// Listを使う為に必要
// 複数のお知らせデータを扱うときに使う
import java.util.List;
// このクラスをServiceとしてSpringに認識させる
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

// NoticeService
// お知らせ機能の業務処理担当
//Serviceの役割
// 入力チェック
// 業務ロジック
// DB操作の依頼
// 重要
// Serviceは頭脳
// Controllerから依頼を受けて実際の処理を行う
@Service

public class NoticeService {

  // ==================================
  // NoticeRepositoryを使う為の変数
  // ===================================
  // RepositoryはDB操作担当
  // finalを付けることで
  // 作成後に別Repositoryへ変更されないようにする
  private final NoticeRepository noticeRepository;

  // =====================================
  // コンストラクタ
  // ====================================
  // springがNoticeRepositoryを自動で渡してくれる
  // これもDI（依存性注入）
  public NoticeService(NoticeRepository noticeRepository) {
    this.noticeRepository = noticeRepository;
  }

  // =================================================
  // お知らせ新規登録処理
  // =================================================
  // titleとcontentを受け取り、DBへ保存する
  public void create(String title, String content) {

    // タイトル未入力チェック
    // null
    // 値そのものが存在しない
    // isBlank()
    // 空文字やスペースのみ
    if (title == null || title.isBlank()) {
      // エラー発生
      // throw
      // 強制的にエラーを発生させる
      throw new IllegalArgumentException("タイトルを入力してください");
    }

    // 本文未入力チェック
    if (content == null || content.isBlank()) {
      throw new IllegalArgumentException("本文を入力してください");
    }

    // Noticeオブジェクト作成
    // DBへ保存するための入れ物
    Notice notice = new Notice();
    // タイトル設定
    notice.setTitle(title);
    // 本文設定
    notice.setContent(content);

    // 現在日時保存
    notice.setCreatedAt(LocalDateTime.now());
    // DB保存
    // save()
    // insert または update
    noticeRepository.save(notice);

  }

  // お知らせ一覧を取得
  // DBから全件取得
  public List<Notice> findAll() {
    // Repositoryに用意した新しい順で取得するメソッドを呼び出す
    // ControllerはServiceを呼ぶだけにして、DB取得の細かい処理はServiceに集約する
    return noticeRepository.findAllByOrderByIdDesc();
  }

  // お知らせを保存
  // 渡されたNoticeを保存
  public void save(Notice notice) {
    // DB保存
    noticeRepository.save(notice);
  }

  // お知らせ削除
  // IDを指定してお知らせを削除する
  public void delete(Long id) {
    // deleteById()
    // 指定IDを削除
    noticeRepository.deleteById(id);
  }

  // お知らせを一件取得
  // idを使ってお知らせ一件取得
  public Notice findById(Long id) {

    // findById()
    // Optional型で返る
    // or Else Throw()
    // データがなければエラー
    return noticeRepository.findById(id).orElseThrow();
  }

  // お知らせ更新
  // idを使って既存データ取得
  public void update(Long id, String title, String content) {

    // タイトルが空ならエラー
    if (title == null || title.isBlank()) {
      throw new IllegalArgumentException("タイトルを入力してください");
    }
    // 本文が空ならエラー
    if (content == null || content.isBlank()) {
      throw new IllegalArgumentException("本文を入力してください");
    }
    // idを使って既存データ取得
    Notice notice = noticeRepository.findById(id).orElseThrow();

    // 新しい内容で上書き
    // setで値変更
    notice.setTitle(title);
    notice.setContent(content);

    // DB更新
    // save()はidが存在している場合はupdateになる
    noticeRepository.save(notice);
  }
}
