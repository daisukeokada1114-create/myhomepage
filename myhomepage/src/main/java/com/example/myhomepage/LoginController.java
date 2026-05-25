package com.example.myhomepage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

// ログイン画面の受付担当
@Controller
public class LoginController {

  //
  // User Repository を使う為の変数
  // final を付けることで、あとから別のRepositoryにさし変わらないよう
  private final UserRepository userRepository;

  // spring が UserRepositoryを自動で渡してくれる
  // これをコンストラクタインジェクション
  public LoginController(UserRepository userRepository) {
    this.userRepository = userRepository;

  }

  // /loginへアクセスされたら
  @GetMapping("/login")
  public String login() {
    return "login";
  }

  // ログインフォームから送信された値を受け取る処理
  @PostMapping("/login")
  public String loginPost(
      // input name="username"の値を受け取る
      @RequestParam String username,

      // input name="password" の値を受け取る
      @RequestParam String password,

      // ブラウザごとのログイン状態を保存する為に使う
      HttpSession session) {

    // DBからusername と password が一致するユーザーを探す
    User user = userRepository.findByUsernameAndPassword(username, password);

    // 一致するユーザーがいなければログイン失敗
    if (user == null) {
      return "ログイン失敗";
    }

    // ログイン成功したユーザー情報をSessionに保存する
    // loginUser という名前で、Userオブジェクトを保存する
    session.setAttribute("loginUser", user);

    // 一致するユーザーがいればログイン成功
    return "redirect:/notice";

  }

}
