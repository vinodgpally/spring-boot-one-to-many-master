package com.example.spring.hibernate.onetomany.controller;

import com.example.spring.hibernate.onetomany.exception.ResourceNotFoundException;
import com.example.spring.hibernate.onetomany.model.Task;
import com.example.spring.hibernate.onetomany.repository.TaskRepository;
import com.example.spring.hibernate.onetomany.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class TaskController {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private TaskRepository taskRepository;

  @GetMapping("/users/{userId}/tasks")
  public ResponseEntity<List<Task>> getAllTasksByUserId(@PathVariable(value = "userId") Long userId) {
    if (!userRepository.existsById(userId)) {
      throw new ResourceNotFoundException("Not found User with id = " + userId);
    }

    List<Task> task = taskRepository.findByUserId(userId);
    return new ResponseEntity<>(task, HttpStatus.OK);
  }

  @GetMapping("/tasks/{taskId}")
  public ResponseEntity<Task> getTasksByTaskId(@PathVariable(value = "taskId") Long taskId) {
    Task task = taskRepository.findById(taskId)
        .orElseThrow(() -> new ResourceNotFoundException("Not found Task with id = " + taskId));

    return new ResponseEntity<>(task, HttpStatus.OK);
  }

  @GetMapping("/users/{userId}/tasks/{taskId}")
  public ResponseEntity<Task> getTasksByIdAndUserId(@PathVariable(value = "userId") Long userId, @PathVariable(value = "taskId") Long taskId) {
//    Task task = taskRepository.findById(taskId)
//            .orElseThrow(() -> new ResourceNotFoundException("Not found Task with id = " + taskId));

    Task task = taskRepository.findByIdAndUserId(taskId, userId).orElseThrow(() -> new ResourceNotFoundException("Not found Task with id = " + taskId));;

    return new ResponseEntity<>(task, HttpStatus.OK);
  }

  @PostMapping("/users/{userId}/tasks")
  public ResponseEntity<Task> createTask(@PathVariable(value = "userId") Long userId,
      @RequestBody Task taskRequest) {
    Task task = userRepository.findById(userId).map(user -> {
      taskRequest.setUser(user);
      return taskRepository.save(taskRequest);
    }).orElseThrow(() -> new ResourceNotFoundException("Not found User with id = " + userId));

    return new ResponseEntity<>(task, HttpStatus.CREATED);
  }

//  @PutMapping("/tasks/{id}")
//  public ResponseEntity<Task> updateTask(@PathVariable("id") long id, @RequestBody Task taskRequest) {
//    Task task = taskRepository.findById(id)
//        .orElseThrow(() -> new ResourceNotFoundException("TaskId " + id + "not found"));
//
//    task.setDescription(taskRequest.getDescription());
//    task.setName(taskRequest.getName());
//    task.setCreateDate(taskRequest.getCreateDate());
//
//
//    return new ResponseEntity<>(taskRepository.save(task), HttpStatus.OK);
//  }

  @PutMapping("/users/{userId}/tasks/{taskId}")
  public ResponseEntity<Task> updateTask(@PathVariable("userId") long userId, @RequestBody Task taskRequest,
                                         @PathVariable("taskId") long taskId) {
//    if (!userRepository.existsById(userId)) {
//      throw new ResourceNotFoundException("Not found User with id = " + userId);
//    }

    Task task = taskRepository.findByIdAndUserId(taskId, userId)
            .orElseThrow(() -> new ResourceNotFoundException("Not found Task with id = " + taskId));;

//    List<Task> tasks = taskRepository.findByUserId(userId);
//    Stream<Task> taskStream = tasks.stream().filter(t -> (t.getId().equals(taskId) && t.getUser().getId().equals(userId)));
//    Optional<Task> first = taskStream.findFirst();

//    task = taskRepository.findById(task.getId())
//            .orElseThrow(() -> new ResourceNotFoundException("TaskId " + id + "not found"));

      if(null != taskRequest.getDescription())
        task.setDescription(taskRequest.getDescription());
    if(null != taskRequest.getName())
      task.setName(taskRequest.getName());
    if(null != taskRequest.getCreateDate())
      task.setCreateDate(taskRequest.getCreateDate());
//    } else
//      throw new ResourceNotFoundException("TaskId " + taskId + "not found");

    return new ResponseEntity<>(taskRepository.save(task), HttpStatus.OK);
  }

  @DeleteMapping("/tasks/{id}")
  public ResponseEntity<HttpStatus> deleteTask(@PathVariable("id") long id) {
    taskRepository.deleteById(id);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @DeleteMapping("/users/{userId}/tasks/{taskId}")
  public ResponseEntity<HttpStatus> deleteTask(@PathVariable("userId") long userId, @PathVariable("taskId") long taskId) {
    if (!userRepository.existsById(userId)) {
      throw new ResourceNotFoundException("Not found User with id = " + userId);
    }

    taskRepository.deleteById(taskId);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }


  
  @DeleteMapping("/users/{userId}/tasks")
  public ResponseEntity<List<Task>> deleteAllTasksOfUser(@PathVariable(value = "userId") Long userId) {
    if (!userRepository.existsById(userId)) {
      throw new ResourceNotFoundException("Not found User with id = " + userId);
    }

    taskRepository.deleteByUserId(userId);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
