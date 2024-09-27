package com.matsugumayudi.todolist.modules.task.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;
import java.util.UUID;

@Getter
@Entity
@NoArgsConstructor(force = true)
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 1024)
    private String description;

    @Column(nullable = false)
    private Boolean completed;

    public Task(String title, String description) {
        this.id = UUID.randomUUID().toString();
        this.setTitle(title);
        this.setDescription(description);
        this.setCompleted(false);
    }

    public Task(String id, String title, String description, Boolean completed) {
        this.id = id;
        this.setTitle(title);
        this.setDescription(description);
        this.setCompleted(completed);
    }

    private void setTitle(String title) {
        this.title = title.trim();
    }

    private void setDescription(String description) {
        this.description = description == null ? "" : description.trim();
    }

    private void setCompleted(Boolean completed) {
        this.completed = completed;
    }
}
