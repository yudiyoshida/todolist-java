package com.matsugumayudi.todolist.modules.task.usecases.updateTask;

import com.matsugumayudi.todolist.modules.task.entities.Task;
import com.matsugumayudi.todolist.modules.task.repositories.TaskRepository;
import com.matsugumayudi.todolist.modules.task.usecases.updateTask.dtos.UpdateTaskInputDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UpdateTaskServiceTest {
    private AutoCloseable closeable;

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private UpdateTaskService updateTaskService;

    @BeforeEach
    void setUp() {
        this.closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        this.closeable.close();
    }

    @Test
    @DisplayName("Should throw an error when task is not found")
    void shouldThrowAnError_whenTaskIsNotFound() {
        // Given
        var id = "1";
        var data = new UpdateTaskInputDto("Title", "Description");

        // When
        var exception = assertThrows(Exception.class, () -> this.updateTaskService.execute(id, data));

        // Then
        assertEquals("Task not found", exception.getMessage());
    }

    @Test
    @DisplayName("Should update a task")
    void shouldUpdateATask() {
        // Given
        var id = "1";
        var data = new UpdateTaskInputDto("Title", "Description");
        var task = new Task("Title", "Description");
        Mockito.when(this.taskRepository.findById(id)).thenReturn(Optional.of(task));

        // When
        var result = this.updateTaskService.execute(id, data);

        // Then
        assertEquals("Task updated", result.message());
    }
}