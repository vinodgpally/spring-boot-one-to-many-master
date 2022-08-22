package com.example.spring.hibernate.onetomany.service.impl;

import com.example.spring.hibernate.onetomany.dto.UserDTO;
import com.example.spring.hibernate.onetomany.exception.ResourceNotFoundException;
import com.example.spring.hibernate.onetomany.model.User;
import com.example.spring.hibernate.onetomany.repository.UserRepository;
import com.example.spring.hibernate.onetomany.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User getUserById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found User with id = " + id));
    }

    @Override
    public User createUser(UserDTO userDto) {
        return userRepository.save(new User(userDto.getUsername(), userDto.getFirst_name(), userDto.getLast_name()));
    }

    @Override
    public User updateUser(long id, UserDTO userDTO) {
        User _user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found User with id = " + id));

        if (!Objects.isNull(userDTO.getUsername()))
            _user.setUsername(userDTO.getUsername());
        if (!Objects.isNull(userDTO.getFirst_name()))
            _user.setFirst_name(userDTO.getFirst_name());
        if (!Objects.isNull(userDTO.getLast_name()))
            _user.setLast_name(userDTO.getLast_name());

        return userRepository.save(_user);
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

    @Override
    public List<User> getAllUsers(String username) {
        List<User> users = new ArrayList<User>();

        if (username == null)
            userRepository.findAll().forEach(users::add);
        else
            userRepository.findByUsernameContaining(username).forEach(users::add);

        return users;
    }
}
