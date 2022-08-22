package com.example.spring.hibernate.onetomany.controller;

import com.example.spring.hibernate.onetomany.dto.TaskDTO;
import com.example.spring.hibernate.onetomany.model.Task;
import com.example.spring.hibernate.onetomany.service.TaskService;
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

@RestController
@RequestMapping("/api")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/user/{userId}/task")
    public ResponseEntity<List<Task>> getAllTasksByUserId(@PathVariable(value = "userId") Long userId) {
        return new ResponseEntity<>(taskService.getAllTasksByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("/task/{taskId}")
    public ResponseEntity<Task> getTasksByTaskId(@PathVariable(value = "taskId") Long taskId) {
        return new ResponseEntity<>(taskService.getTasksByTaskId(taskId), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/task/{taskId}")
    public ResponseEntity<Task> getTasksByIdAndUserId(@PathVariable(value = "userId") Long userId, @PathVariable(value = "taskId") Long taskId) {
        return new ResponseEntity<>(taskService.getTasksByIdAndUserId(userId, taskId), HttpStatus.OK);
    }

    @PostMapping("/user/{userId}/task")
    public ResponseEntity<Task> createTask(@PathVariable(value = "userId") Long userId, @RequestBody TaskDTO taskRequest) {
        return new ResponseEntity<>(taskService.createTask(userId, taskRequest), HttpStatus.CREATED);
    }

    @PutMapping("/user/{userId}/task/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable("userId") long userId, @RequestBody TaskDTO taskRequest,
                                           @PathVariable("taskId") long taskId) {
        return new ResponseEntity<>(taskService.updateTask(userId, taskRequest, taskId), HttpStatus.OK);
    }

    @DeleteMapping("/task/{id}")
    public ResponseEntity<HttpStatus> deleteTask(@PathVariable("id") long id) {
        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/user/{userId}/task/{taskId}")
    public ResponseEntity<HttpStatus> deleteTask(@PathVariable("userId") long userId, @PathVariable("taskId") long taskId) {
        taskService.deleteTask(userId, taskId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @DeleteMapping("/user/{userId}/task")
    public ResponseEntity<HttpStatus> deleteAllTasksOfUser(@PathVariable(value = "userId") Long userId) {
        taskService.deleteAllTasksOfUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
