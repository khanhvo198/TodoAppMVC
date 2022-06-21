package com.mystic.TodoAppMVC.repo;

import com.mystic.TodoAppMVC.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepo extends JpaRepository<Task, Long> {

    Optional<Task> findTaskById(Long id);
    List<Task> findByUserId(Long id);

}
