package com.mystic.TodoAppMVC.controller;


import com.mystic.TodoAppMVC.DTO.UserDTO;
import com.mystic.TodoAppMVC.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;
    @GetMapping("/registration")
    public ModelAndView newUser() {
        return new ModelAndView("/registration/registration");
    }

    @PostMapping("/registration")
    public String register(@ModelAttribute UserDTO user) {
        registrationService.register(user);
        return "redirect:/login";
    }





}
