package com.mystic.TodoAppMVC.repo;

import com.mystic.TodoAppMVC.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepo extends JpaRepository<Task, Long> {


}
