package com.mystic.TodoAppMVC.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mystic.TodoAppMVC.DTO.UserDTO;
import com.mystic.TodoAppMVC.service.RegistrationService;
import com.mystic.TodoAppMVC.service.UserService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;
    private final UserService userService;
    @GetMapping("/registration")
    public ModelAndView newUser() {
        return new ModelAndView("registration/registration");
    }

    @PostMapping("/registration")
    public String register(@ModelAttribute UserDTO user, RedirectAttributes redirectAttr) {
        boolean existedUser = userService.isExist(user);

        boolean flagError = false;
        String message = "";

        if(existedUser) {
          message = "Email already taken, please use another email!";
          flagError = true;
        }

        if(user.getEmail().isEmpty()) {
          message = "Email cannot be empty!";
          flagError = true;
        }

        if(user.getPassword().isEmpty()) {
          message = "Password cannot be empty!";
          flagError = true;
        }

        if(flagError) {
          redirectAttr.addFlashAttribute("message", message);
          redirectAttr.addFlashAttribute("error", true);
          return "redirect:/registration";
        } else {
          redirectAttr.addFlashAttribute("message", "Create user successfully!");
          redirectAttr.addFlashAttribute("success", true);

          registrationService.register(user);
          return "redirect:/login";
        }
    }

}
