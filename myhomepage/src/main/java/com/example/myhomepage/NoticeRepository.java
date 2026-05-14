package com.example.myhomepage;

import org.springframework.data.jpa.repository.JpaRepository;

// DB操作担当
public interface NoticeRepository extends JpaRepository<Notice, Long> {

}
