package com.example.myhomepage;

// Listを使う為
import java.util.List;

// 画面表示用のcontrolleraを使う為に読み込む
import org.springframework.stereotype.Controller;

// HTMLへデータを渡すため
import org.springframework.ui.Model;

// URLとメソッドを結びつけるために読み込む
import org.springframework.web.bind.annotation.GetMapping;

// このクラスはcontorollerです とspringboot へ伝える
@Controller

public class HomeController {

  // http://localhost:8080/にアクセスした時に実行される
  @GetMapping("/")
  public String home(Model model) {

    // model

    // HTMLへデータを渡す箱

    // 店名データ
    String salonName = "架空のサロン名";

    // キャッチコピー
    String concept = "ここにサロンのテーマを書くとよい";

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

    // templates/index.html を表示する
    return "index";

  }

}
