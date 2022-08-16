package com.example.spring.hibernate.onetomany.schedules;

import com.example.spring.hibernate.onetomany.model.Task;
import com.example.spring.hibernate.onetomany.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class UpdateTasksTimer { //extends TimerTask
//    @Override
//    public void run() {
//        System.out.println("Email sent at: "
//                + LocalDateTime.ofInstant(Instant.ofEpochMilli(scheduledExecutionTime()),
//                ZoneId.systemDefault()));
//    }

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private static final String pending = "pending";
    private static final String done = "done";

    @Autowired
    private TaskRepository taskRepository;

    @Scheduled(fixedDelay = 5000)
    public void reportCurrentTime() {

        System.out.println("The time is now " + dateFormat.format(new Date()));

        List<Task> pendingTasks = taskRepository.findByStatusAndCreateDateBefore(pending, new Date());
        pendingTasks.stream().forEach(pendingTask -> {
            System.out.println(pendingTask);
            pendingTask.setStatus(done);
            taskRepository.save(pendingTask);
        });

    }
}
