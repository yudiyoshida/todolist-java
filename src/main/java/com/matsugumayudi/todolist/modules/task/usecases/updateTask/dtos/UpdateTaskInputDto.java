package com.matsugumayudi.todolist.modules.task.usecases.updateTask.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateTaskInputDto(
    @NotNull(message = "Title cannot be null")
    @NotBlank(message = "Title is required")
    String title,

    String description
) { }
