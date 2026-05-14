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
}
