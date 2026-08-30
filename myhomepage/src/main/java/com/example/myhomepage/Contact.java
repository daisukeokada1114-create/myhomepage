package com.example.myhomepage;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Contact {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank(message = "名前を入力してください")
  @Size(max = 100, message = "名前は100文字以内で入力してください")
  private String name;

  @NotBlank(message = "メールアドレスを入力してください")
  @Email(message = "正しいメールアドレスを入力してください")
  @Size(max = 255, message = "メールアドレスは255文字以内で入力してください")
  private String email;

  @NotBlank(message = "お問い合わせ内容を入力してください")
  @Size(max = 2000, message = "お問い合わせ内容は2000文字以内で入力してください")
  private String message;

  private LocalDateTime createdAt;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }
}
