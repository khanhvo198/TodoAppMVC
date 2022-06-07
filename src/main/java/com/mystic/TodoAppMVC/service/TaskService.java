package com.mystic.TodoAppMVC.service;


import com.mystic.TodoAppMVC.exception.TaskNotFoundException;
import com.mystic.TodoAppMVC.model.Task;
import com.mystic.TodoAppMVC.repo.TaskRepo;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TaskService {

    private final TaskRepo taskRepo;

    public TaskService(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    public Task addTask(Task task) { return taskRepo.save(task); }

    public List<Task> findAllTasks() {
        return taskRepo.findAll();
    }

    public Task findTaskById(Long id) {
        return taskRepo.findTaskById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task " + id + "was not found"));
    }

    public Task updateTask(Task task) {
        return taskRepo.save(task);
    }

    public void deleteTaskById(Long id) {
        taskRepo.deleteById(id);
    }


}
