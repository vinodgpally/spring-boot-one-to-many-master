package com.example.spring.hibernate.onetomany.repository;

import com.example.spring.hibernate.onetomany.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
  List<Task> findByUserId(Long userId);
  
//  @Transactional
  void deleteByUserId(Long userId);

  Optional<Task> findByIdAndUserId(Long id, Long userId);

  List<Task> findByStatusAndCreateDateBefore(String status, Date createDate);

}
