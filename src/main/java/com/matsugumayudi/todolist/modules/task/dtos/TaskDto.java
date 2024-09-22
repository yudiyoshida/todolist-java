package com.matsugumayudi.todolist.modules.task.dtos;

import com.matsugumayudi.todolist.modules.task.entities.Task;
import lombok.Getter;

@Getter
public class TaskDto {
    private final String id;
    private final String title;
    private final String description;
    private final Boolean completed;

    public TaskDto(Task task) {
        this.id = task.getId();
        this.title = task.getTitle();
        this.description = task.getDescription();
        this.completed = task.getCompleted();
    }
}
