package com.example.spring.hibernate.onetomany.schedules;

import com.example.spring.hibernate.onetomany.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class UpdateTasksTimer { //extends TimerTask

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private static final String pending = "pending";
    private static final String done = "done";

    @Autowired
    private TaskService taskService;

    @Scheduled(fixedDelay = 30000)
    public void updatePendingTask() {
        taskService.updatePendingTask(pending, new Date(), done);
    }
}
