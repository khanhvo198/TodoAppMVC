package com.mystic.TodoAppMVC.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.mystic.TodoAppMVC.model.Task;
import com.mystic.TodoAppMVC.service.TaskService;


@Controller
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("task/index");
        List<Task> tasks = taskService.findAllTasks();
        mav.addObject("tasks", tasks);
        return mav;
    }

    @GetMapping("/new")
    public ModelAndView newTask() {
        ModelAndView mav = new ModelAndView("task/create");
        Task newTask = new Task();
        mav.addObject("task", newTask);
        return  mav;
    }

    @PostMapping("/create")
    public String createTask(@ModelAttribute Task task) {
        taskService.addTask(task);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editTask(@PathVariable("id") Long id) {
        ModelAndView mav = new ModelAndView("task/edit");
        Task task = taskService.findTaskById(id);
        mav.addObject("task", task);
        return mav;
    }

    @PostMapping("/update")
    public String updateTask(@ModelAttribute Task task) {
        taskService.updateTask(task);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable("id") Long id) {
        taskService.deleteTaskById(id);
        return "redirect:/";
    }

    @PostMapping("/update/{id}/complete")
    public String updateTaskComplete(@PathVariable("id") Long id, @ModelAttribute Task updateTask ) {
      Task task = taskService.findTaskById(id);
      // System.out.print(task.isChecked());
      // System.out.print(updateTask.isChecked());
      task.setChecked(!task.isChecked());
      // task.setChecked(updateTask.isChecked());
      taskService.updateTask(task);
      return "redirect:/";
    }







}
