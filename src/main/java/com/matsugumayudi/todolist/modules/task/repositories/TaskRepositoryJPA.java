package com.matsugumayudi.todolist.modules.task.repositories;

import com.matsugumayudi.todolist.modules.task.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepositoryJPA extends JpaRepository<Task, String> { }
