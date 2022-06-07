package com.mystic.TodoAppMVC.service;


import java.util.List;
import org.springframework.stereotype.Service;
import com.mystic.TodoAppMVC.model.Task;
import com.mystic.TodoAppMVC.repo.TaskRepo;


@Service
public class TaskService {

    private final TaskRepo taskRepo;

    public TaskService(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    public Task addTask(Task task) {
        return taskRepo.save(task);
    }

    public List<Task> findAllTasks() {
        return taskRepo.findAll();
    }

}
