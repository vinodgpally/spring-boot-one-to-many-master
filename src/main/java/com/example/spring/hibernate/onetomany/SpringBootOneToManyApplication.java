package com.example.spring.hibernate.onetomany;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringBootOneToManyApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringBootOneToManyApplication.class, args);
  }

}
