package com.example.myhomepage;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

// お知らせ画面の受付担当
@Controller

public class NoticeController {
  private final NoticeService noticeService;

  // springがNoticeServiceを自動で渡してくれる
  public NoticeController(NoticeService noticeService) {
    this.noticeService = noticeService;
  }

  // noticeにアクセスされたときの処理
  @GetMapping("/notice")
  public String notice(Model model) {

    // DBから取得したお知らせ一覧をHTMLへ渡す
    model.addAttribute("noticeList", noticeService.findAll());

    // templates/notice.htmlを表示
    return "notice";
  }

  // お知らせ登録処理
  @PostMapping("/notice/create")
  public String create(Notice notice) {

    // 入力されたお知らせをDBに保存
    noticeService.save(notice);

    // 保存後、一覧画面へ戻る
    return "redirect:/notice";
  }

  // お知らせ削除処理
  @PostMapping("/notice/delete")
  public String delete(@RequestParam Long id) {

    // 指定されたIDのお知らせ削除
    noticeService.delete(id);

    // 削除後、一覧画面に戻る
    return "redirect:/notice";
  }

}
