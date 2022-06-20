package com.mystic.TodoAppMVC.controller;


import com.mystic.TodoAppMVC.DTO.UserDTO;
import com.mystic.TodoAppMVC.model.User;
import com.mystic.TodoAppMVC.service.RegistrationService;
import lombok.AllArgsConstructor;
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
        ModelAndView mav = new ModelAndView("/registration/registration");
//        UserDTO user = new UserDTO();
//        mav.addObject("user",user);
        return mav;
    }

    @PostMapping("/registration")
    public String register(@ModelAttribute UserDTO user) {
        registrationService.register(user);
        return "redirect:/login";
    }





}
