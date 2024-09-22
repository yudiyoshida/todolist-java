package com.matsugumayudi.todolist.modules.task.entities;

import lombok.Getter;

import java.util.UUID;

@Getter
public class Task {
    private String id;
    private String title;
    private String description;
    private Boolean completed;

    public Task(String title, String description) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.completed = false;
    }

    public Task(String id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.completed = false;
    }
}
