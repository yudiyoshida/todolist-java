package com.matsugumayudi.todolist.task.usecases.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

public record CreateTaskInputDto(
    @NotNull(message = "Title cannot be null")
    @NotBlank(message = "Title is required")
    String title,

    String description
) {}
