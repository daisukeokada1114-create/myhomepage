package com.example.myhomepage;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class LoginCheckInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(
      HttpServletRequest request,
      HttpServletResponse response,
      Object handler)
      throws Exception {

    // 現在のセッション情報を取得する
    HttpSession session = request.getSession();

    // セッション内に loginUser があるか確認する
    if (session.getAttribute("loginUser") == null) {

      // 未ログインならログイン画面へ移動される
      response.sendRedirect("/login");

      // Controllerの処理をとめる
      return false;
    }
    // ログイン済みなら Controller の処理を続ける
    return true;
  }

}
