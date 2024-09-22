package com.matsugumayudi.todolist.task.entities;

import com.matsugumayudi.todolist.task.usecases.dtos.CreateTaskInputDto;
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
        this.title = data.title();
        this.description = data.description();
        this.completed = false;
    }
}
