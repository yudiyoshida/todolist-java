package com.matsugumayudi.todolist.modules.task.usecases.deleteTask;

import com.matsugumayudi.todolist.modules.task.entities.Task;
import com.matsugumayudi.todolist.modules.task.repositories.TaskRepository;
import com.matsugumayudi.todolist.modules.task.repositories.TaskRepositoryJPA;
import com.matsugumayudi.todolist.shared.dtos.SuccessMessage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Closeable;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class DeleteTaskServiceTest {
    private AutoCloseable closeable;

    @Mock
    private TaskRepositoryJPA taskRepository;

    @InjectMocks
    private DeleteTaskService deleteTaskService;

    @BeforeEach
    void setUp() {
        this.closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        this.closeable.close();
    }

    @Test
    @DisplayName("Should throw an error when task does not exist")
    void shouldThrowError_whenTaskNotFound() {
        // Arrange
        String id = "random-id";
        Mockito.when(this.taskRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        Throwable thrown = assertThrows(Exception.class, () -> this.deleteTaskService.execute(id));

        // Assert
        assertEquals("Task not found", thrown.getMessage());
    }

    @Test
    @DisplayName("Should delete a task and return a success message")
    void shouldDeleteTaskAndReturnSuccessMessage() {
        // Arrange
        String id = "random-id";
        Task task = new Task("title", "description");
        Mockito.when(this.taskRepository.findById(id)).thenReturn(Optional.of(task));

        // Act
        SuccessMessage result = this.deleteTaskService.execute(id);

        // Assert
        Mockito.verify(this.taskRepository, Mockito.times(1)).delete(task);
        assertEquals("Task deleted", result.message());
    }
}