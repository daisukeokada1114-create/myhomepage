package com.example.myhomepage;

// Listを使う為
import java.util.List;

// 画面表示用のcontrolleraを使う為に読み込む
import org.springframework.stereotype.Controller;

// HTMLへデータを渡すため
import org.springframework.ui.Model;

// URLとメソッドを結びつけるために読み込む
import org.springframework.web.bind.annotation.GetMapping;

// このクラスはcontrollerです とspring boot へ伝える
@Controller

public class HomeController {

  // NoticeServiceを使う為の変数
  // finalを付けることで、途中で別のものに入れ替わらないようにする
  private final NoticeService noticeService;

  // spring が NoticeServiceを自動で渡してくれる
  // これをコンストラクションという
  public HomeController(NoticeService noticeService) {
    this.noticeService = noticeService;
  }

  // http://localhost:8080/にアクセスした時に実行される
  @GetMapping("/")
  public String home(Model model) {

    // トップページ用固定データ
    // ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝
    // 店名データ
    String salonName = "架空の店名";

    // キャッチコピー
    String concept = "キャッチコピー";

    // メニュー名のリスト
    // 今は、DBではなく、javaの中で仮データを作る
    List<String[]> menus = List.of(

        new String[] { "メニュー１", "値段", "メニュー説明" },
        new String[] { "メニュー２", "値段", "メニュー説明" },
        new String[] { "メニュー３", "値段", "メニュー説明" });

    // HTMLへデータを渡す
    // salonName => HTML側の名前
    // salonName => Java側の変数

    model.addAttribute("salonName", salonName);
    model.addAttribute("concept", concept);

    // HTMLへメニュー 一覧を渡す
    model.addAttribute("menus", menus);

    // DBから取得したお知らせ一覧をトップページへ渡す
    model.addAttribute("noticeList", noticeService.findAll());

    // templates/index.html を表示する
    return "index";

  }

}
