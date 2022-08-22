package com.example.spring.hibernate.onetomany.service.impl;

import com.example.spring.hibernate.onetomany.dto.TaskDTO;
import com.example.spring.hibernate.onetomany.exception.ResourceNotFoundException;
import com.example.spring.hibernate.onetomany.model.Task;
import com.example.spring.hibernate.onetomany.repository.TaskRepository;
import com.example.spring.hibernate.onetomany.repository.UserRepository;
import com.example.spring.hibernate.onetomany.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TaskRepository taskRepository;

    @Override
    public List<Task> getAllTasksByUserId(Long userId) {
        if (!userRepository.existsById(userId))
            throw new ResourceNotFoundException("Not found User with id = " + userId);

        return taskRepository.findByUserId(userId);
    }

    @Override
    public Task getTasksByTaskId(Long taskId) {
        return taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Task with id = " + taskId));
    }

    @Override
    public Task getTasksByIdAndUserId(Long userId, Long taskId) {
        return taskRepository.findByIdAndUserId(taskId, userId).orElseThrow(() -> new ResourceNotFoundException("Not found Task with id = " + taskId));
    }

    @Override
    public Task createTask(Long userId, TaskDTO taskRequest) {
        return userRepository.findById(userId).map(user -> {
            Task t = new Task(taskRequest.getName(), taskRequest.getDescription(), taskRequest.getCreateDate());
            t.setUser(user);
            t.setStatus("pending");
            return taskRepository.save(t);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found User with id = " + userId));
    }

    @Override
    public Task updateTask(long userId, TaskDTO taskRequest, long taskId) {
        Task task = taskRepository.findByIdAndUserId(taskId, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Task with id = " + taskId));

        if (null != taskRequest.getDescription())
            task.setDescription(taskRequest.getDescription());
        if (null != taskRequest.getName())
            task.setName(taskRequest.getName());
        if (null != taskRequest.getCreateDate())
            task.setCreateDate(taskRequest.getCreateDate());

        return taskRepository.save(task);
    }

    @Override
    public void deleteTask(long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public void deleteTask(long userId, long taskId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("Not found User with id = " + userId);
        }
        taskRepository.deleteById(taskId);
    }

    @Override
    public void deleteAllTasksOfUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("Not found User with id = " + userId);
        }
        taskRepository.deleteByUserId(userId);
    }

    @Override
    public void updatePendingTask(String pendingStatus, Date currentDate, String doneStatus) {
        List<Task> pendingTasks = taskRepository.findByStatusAndCreateDateBefore(pendingStatus, currentDate);
        pendingTasks.stream().forEach(task -> {
            task.setStatus(doneStatus);
        });
        pendingTasks = taskRepository.saveAll(pendingTasks);
        pendingTasks.stream().forEach(System.out::println);
    }
}
