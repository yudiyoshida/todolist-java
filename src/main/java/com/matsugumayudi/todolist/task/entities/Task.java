package com.matsugumayudi.todolist.task.entities;

import com.matsugumayudi.todolist.task.usecases.createtask.dtos.CreateTaskInputDto;
import lombok.Getter;

import java.util.UUID;

@Getter
public class Task {
    private String id;
    private String title;
    private String description;
    private Boolean completed;

    public Task(CreateTaskInputDto data) {
        this.id = UUID.randomUUID().toString();
        this.title = data.title().trim();
        this.description = data.description().trim();
        this.completed = false;
    }
}
