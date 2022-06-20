package com.mystic.TodoAppMVC.repo;

import com.mystic.TodoAppMVC.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository< User, Long> {
    Optional<User> findByEmail(String email);

}
