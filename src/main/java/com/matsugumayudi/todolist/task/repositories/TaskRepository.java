package com.matsugumayudi.todolist.task.repositories;

import com.matsugumayudi.todolist.task.entities.Task;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class TaskRepository {
    private final Map<String, Task> tasks = new HashMap<>();

    public void save(Task task) {
        this.tasks.put(task.getId(), task);
    }

    public List<Task> findAll() {
        return this.tasks.values().stream().toList();
    }

    public Optional<Task> findById(String id) {
        return Optional.ofNullable(this.tasks.get(id));
    }

    public void edit(Task task) {
        this.tasks.put(task.getId(), task);
    }
}
