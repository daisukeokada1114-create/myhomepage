package com.example.myhomepage;

import java.util.List;

import org.springframework.stereotype.Service;

@Service

public class NoticeService {

  private final NoticeRepository noticeRepository;

  // springがNoticeRepositoryを自動で渡してくれる
  public NoticeService(NoticeRepository noticeRepository) {
    this.noticeRepository = noticeRepository;
  }

  // お知らせ一覧を取得
  public List<Notice> findAll() {
    return noticeRepository.findAll();
  }

  // お知らせを保存
  public void save(Notice notice) {
    noticeRepository.save(notice);
  }

  // IDを指定してお知らせを削除する
  public void delete(Long id) {
    noticeRepository.deleteById(id);
  }

  // idを使ってお知らせ一件取得
  public Notice findById(Long id) {
    return noticeRepository.findById(id).orElseThrow();
  }

  // お知らせ更新
  public void update(Long id, String title, String content) {

    // idを使って既存データ取得
    Notice notice = noticeRepository.findById(id).orElseThrow();

    // 新しい内容で上書き
    notice.setTitle(title);
    notice.setContent(content);

    // DB保存
    noticeRepository.save(notice);
  }
}
