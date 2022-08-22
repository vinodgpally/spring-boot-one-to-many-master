package com.example.spring.hibernate.onetomany.service;

import com.example.spring.hibernate.onetomany.dto.TaskDTO;
import com.example.spring.hibernate.onetomany.model.Task;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface TaskService {

    public List<Task> getAllTasksByUserId(Long userId);

    public Task getTasksByTaskId(Long taskId);

    public Task getTasksByIdAndUserId(Long userId, Long taskId);

    public Task createTask(Long userId, TaskDTO taskRequest);

    public Task updateTask(long userId, TaskDTO taskRequest, long taskId);

    public void deleteTask(long id);

    public void deleteTask(long userId, long taskId);

    public void deleteAllTasksOfUser(Long userId);

    public void updatePendingTask(String pending, Date currentDate, String doneStatus);
}
