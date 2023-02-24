package com.comarch.bootcamp.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class JdbcApplication {

  public static void main(String[] args) {
    SpringApplication.run(JdbcApplication.class, args);
  }
}
