package com.matsugumayudi.todolist.task.repositories;

import com.matsugumayudi.todolist.task.entities.Task;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TaskRepository {
    private final List<Task> tasks = new ArrayList<>();

    public void save(Task task) {
        this.tasks.add(task);
    }

    public List<Task> findAll() {
        return this.tasks;
    }
}
