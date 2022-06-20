package com.mystic.TodoAppMVC.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.mystic.TodoAppMVC.model.Task;
import com.mystic.TodoAppMVC.service.TaskService;


@Controller
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping("/tasks")
    @PreAuthorize("hasAuthority('USER')")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("task/index");
        List<Task> tasks = taskService.findAllTasks();
        mav.addObject("tasks", tasks);
        return mav;
    }

    @GetMapping("/tasks/new")
    @PreAuthorize("hasAuthority('USER')")
    public ModelAndView newTask() {
        ModelAndView mav = new ModelAndView("task/create");
        Task newTask = new Task();
        mav.addObject("task", newTask);
        return  mav;
    }

    @PostMapping("/tasks/create")
    @PreAuthorize("hasAuthority('USER')")
    public String createTask(@ModelAttribute Task task) {
        taskService.addTask(task);
        return "redirect:/tasks";
    }

    @GetMapping("/tasks/edit/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ModelAndView editTask(@PathVariable("id") Long id) {
        ModelAndView mav = new ModelAndView("task/edit");
        Task task = taskService.findTaskById(id);
        mav.addObject("task", task);
        return mav;
    }

    @PostMapping("/tasks/update")
    @PreAuthorize("hasAuthority('USER')")
    public String updateTask(@ModelAttribute Task task) {
        taskService.updateTask(task);
        return "redirect:/tasks";
    }

    @GetMapping("/tasks/delete/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public String deleteTask(@PathVariable("id") Long id) {
        taskService.deleteTaskById(id);
        return "redirect:/tasks";
    }

    @PostMapping("/tasks/update/{id}/complete")
    @PreAuthorize("hasAuthority('USER')")
    public String updateTaskComplete(@PathVariable("id") Long id, @ModelAttribute Task updateTask ) {
      Task task = taskService.findTaskById(id);
      task.setChecked(!task.isChecked());
      taskService.updateTask(task);
      return "redirect:/tasks";
    }







}
