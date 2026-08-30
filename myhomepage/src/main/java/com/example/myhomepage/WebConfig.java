package com.example.myhomepage;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// Spring MVCの設定を追加するクラス
@Configuration
public class WebConfig implements WebMvcConfigurer {

  private final LoginCheckInterceptor loginCheckInterceptor;

  // SpringがLoginCheckInterceptor を自動で渡してくれる
  public WebConfig(LoginCheckInterceptor loginCheckInterceptor) {
    this.loginCheckInterceptor = loginCheckInterceptor;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {

    // 管理系URLをログイン必須にする
    registry.addInterceptor(loginCheckInterceptor)
        .addPathPatterns("/notice", "/notice/**", "/admin", "/admin/**");
  }
}
