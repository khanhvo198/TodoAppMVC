package com.mystic.TodoAppMVC.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mystic.TodoAppMVC.DTO.UserDTO;
import com.mystic.TodoAppMVC.model.User;
import com.mystic.TodoAppMVC.repo.UserRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;
    private final PasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
      return userRepo.findByEmail(email)
              .orElseThrow(()->
                      new UsernameNotFoundException(
                              String.format("User %s not found", email)));

    }


    public void signUpUser(User user) {
      String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
      user.setPassword(encodedPassword);
      userRepo.save(user);
    }

    public boolean isExist(UserDTO user) {
        return userRepo.findByEmail(user.getEmail()).isPresent();
    }
    
}
