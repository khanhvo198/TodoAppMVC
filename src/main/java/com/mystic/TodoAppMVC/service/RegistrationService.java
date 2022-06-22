package com.mystic.TodoAppMVC.service;

import org.springframework.stereotype.Service;

import com.mystic.TodoAppMVC.DTO.UserDTO;
import com.mystic.TodoAppMVC.model.User;
import com.mystic.TodoAppMVC.role.UserRole;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class RegistrationService {

    private final UserService userService;

    public void register(UserDTO userDTO) {
        userService.signUpUser(new User(
                userDTO.getFirstName(),
                userDTO.getLastName(),
                userDTO.getEmail(),
                userDTO.getPassword(),
                UserRole.USER
        ));
    }



}
