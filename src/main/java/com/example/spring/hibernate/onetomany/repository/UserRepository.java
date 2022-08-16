package com.example.spring.hibernate.onetomany.repository;

import com.example.spring.hibernate.onetomany.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
//  List<Tutorial> findByPublished(boolean published);
//
  List<User> findByUsernameContaining(String title);
}
