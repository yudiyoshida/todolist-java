package com.matsugumayudi.todolist.task.repositories;

import com.matsugumayudi.todolist.task.entities.Task;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TaskRepository {
    private final List<Task> tasks = new ArrayList<>();

    public void save(Task task) {
        this.tasks.add(task);
    }

    public List<Task> findAll() {
        return this.tasks;
    }

    public Optional<Task> findById(String id) {
        return this.tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst();
    }
}
