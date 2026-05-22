package com.example.myhomepage;

// このクラスをcontrollerとしてspringに認識させるため
import org.springframework.stereotype.Controller;
// java側からHTMLへデータを渡す為に使う
import org.springframework.ui.Model;
// ブラウザからGETリクエストが来た時のＵＲＬを指定する
import org.springframework.web.bind.annotation.GetMapping;
// ブラウザからpostリクエストが来た時のＵＲＬを指定する
import org.springframework.web.bind.annotation.PostMapping;
// フォームやＵＲＬから送られてきた値を受け取る為に使う
import org.springframework.web.bind.annotation.RequestParam;

// お知らせ機能のcontrollerクラス
// controllerの役割は、ブラウザから来たリクエストを受け取り、
// 必要な処理をserviceに依頼し、
// 最後にどのhtmlを表示するか、またどこへリダイレクトするかを決めること

// 重要
// controllerは基本的に受付係
// DB保存.削除、更新などの具体的な処理はserviceに任せる
@Controller

public class NoticeController {
  // NoticeServiceを使う為のフィールド
  // finalを付けることで
  // このcontrollerが作られた後に別のserviceへ差し替えられないようにしている
  private final NoticeService noticeService;

  // コントラクタ
  // springがNotice controllerをつくるときに
  // NoticeServiceを自動で渡してくれる
  // これをDIまたは依存性の注入という
  // new NoticeService()と、自分で書かなくても、springが必要な部品を自動で用意してくれる
  public NoticeController(NoticeService noticeService) {
    this.noticeService = noticeService;
  }

  // noticeにアクセスされたときの処理
  @GetMapping("/notice")
  public String notice(Model model) {

    // Serviceに依頼してDBからお知らせ一覧を取得する
    // noticeService.findAll()
    // DBに保存されているお知らせ一覧を取得する
    // model.addAttributeでHTMLへ渡す
    // noticeListという名前でHTMLへ渡す
    // Thyme leaf側では${noticeList} として使える
    model.addAttribute("noticeList", noticeService.findAll());

    // templates/notice.htmlを表示
    return "notice";
  }

  // お知らせ新規登録処理
  // notice.htmlのフォームからtitleとcontentがpost送信されたときに動く

  @PostMapping("/notice/create")
  public String create(
      // フォームのinput name=titleの値を受け取る
      @RequestParam String title,
      // textarea name ="content"の値を受け取る
      @RequestParam String content,
      // HTMLへデータを渡す為に使う
      Model model) {
    try {
      // Serviceへ登録処理を依頼
      noticeService.create(title, content);
      // 登録成功後
      // 一覧画面へ戻る
      // redirect ブラウザへ再アクセス指示
      return "redirect:/notice";
    } catch (IllegalArgumentException e) {
      // Service側で入力チェックエラーが起きた場合
      // エラーメッセージをHTMLへ渡す
      model.addAttribute("errorMessage", e.getMessage());

      // 入力したタイトルをもう一度HTMLへ渡す
      model.addAttribute("content", content);

      // 一覧も再表示するため再取得
      model.addAttribute("noticeList", noticeService.findAll());
      // notice.htmlへ戻す
      return "notice";
    }

  }

  // お知らせ削除処理
  // 削除ボタン押したときに動く
  @PostMapping("/notice/delete")
  public String delete(
      // 削除対象IDを受け取る
      @RequestParam Long id) {

    // 指定されたIDのお知らせ削除
    noticeService.delete(id);

    // 削除後、一覧画面に戻る
    return "redirect:/notice";
  }

  // 編集画面を表示する
  // URL例 notice/edit?id=1
  @GetMapping("/notice/edit")
  public String edit(
      // URLからidを受け取る
      @RequestParam Long id,
      Model model) {

    // idを使ってDBから1件取得する
    Notice notice = noticeService.findById(id);

    // edit.htmlに渡す ${notice}として使える
    model.addAttribute("notice", notice);

    // edit.htmlを表示する
    return "edit";
  }

  // 編集内容を更新する
  // edit.htmlから送信された値でDB更新を行う
  @PostMapping("/notice/update")
  public String update(
      // 更新対象ID
      @RequestParam Long id,
      // 更新後タイトル
      @RequestParam String title,
      // 更新後本文
      @RequestParam String content,
      Model model) {

    try {
      // Serviceへ更新処理依頼
      noticeService.update(id, title, content);
      // 更新後戻る
      return "redirect:/notice";
    } catch (IllegalArgumentException e) {

      // エラーメッセージをHTMLへ渡す
      model.addAttribute("errorMessage", e.getMessage());
      // 入力途中の内容をもう一度画面に表示するために、Noticeに詰めなおす
      Notice notice = new Notice();
      notice.setId(id);
      notice.setTitle(title);
      notice.setContent(content);

      // edit.htmlへ渡す
      model.addAttribute("notice", notice);

      // 編集画面に戻る
      return "edit";
    }

  }

}
