package com.mystic.TodoAppMVC.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import com.mystic.TodoAppMVC.model.Task;
import com.mystic.TodoAppMVC.service.TaskService;


@Controller
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping("/")
    public ModelAndView getAllTasks() {
        ModelAndView mav = new ModelAndView("index");
        List<Task> tasks = taskService.findAllTasks();
        mav.addObject(tasks);
        return mav;
    }

    @GetMapping("/new")
    public ModelAndView newTask() {
        ModelAndView mav = new ModelAndView("create_task");
        Task newTask = new Task();
        mav.addObject(newTask);
        return  mav;
    }

}
