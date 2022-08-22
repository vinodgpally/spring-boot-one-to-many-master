package com.example.spring.hibernate.onetomany.controller;

import com.example.spring.hibernate.onetomany.dto.UserDTO;
import com.example.spring.hibernate.onetomany.model.User;
import com.example.spring.hibernate.onetomany.service.UserService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class UserController {

  @Autowired
  UserService userService;

  @GetMapping("/user")
  public ResponseEntity<List<User>> getAllUsers(@RequestParam(required = false) String username) {
    List<User> users = userService.getAllUsers(username);
    if (users.isEmpty())
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    return new ResponseEntity<>(users, HttpStatus.OK);
  }

  @GetMapping("/user/{id}")
  public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
    return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
  }

  @PostMapping("/user")
  public ResponseEntity<User> createUser(@RequestBody UserDTO userDto) {
    return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);
  }

  @PutMapping("/user/{id}")
  public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody UserDTO userDTO) {
    return new ResponseEntity<>(userService.updateUser(id, userDTO), HttpStatus.OK);
  }

  @DeleteMapping("/user/{id}")
  public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id) {
    userService.deleteUser(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @DeleteMapping("/user")
  public ResponseEntity<HttpStatus> deleteAllUsers() {
    userService.deleteAll();
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
