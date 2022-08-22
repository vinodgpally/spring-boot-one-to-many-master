package com.example.spring.hibernate.onetomany.service;

import com.example.spring.hibernate.onetomany.dto.UserDTO;
import com.example.spring.hibernate.onetomany.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    public User getUserById(long id);

    public User createUser(UserDTO userDto);

    public User updateUser(long id, UserDTO userDto);

    public void deleteUser(long id);

    public void deleteAll();

    public List<User> getAllUsers(String username);
}
