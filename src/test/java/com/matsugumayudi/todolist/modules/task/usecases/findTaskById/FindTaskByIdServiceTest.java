package com.matsugumayudi.todolist.modules.task.usecases.findTaskById;

import com.matsugumayudi.todolist.modules.task.dtos.TaskDto;
import com.matsugumayudi.todolist.modules.task.entities.Task;
import com.matsugumayudi.todolist.modules.task.repositories.TaskRepository;
import com.matsugumayudi.todolist.modules.task.repositories.TaskRepositoryJPA;
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

class FindTaskByIdServiceTest {
    private AutoCloseable closeable;

    @Mock
    private TaskRepositoryJPA taskRepository;

    @InjectMocks
    private FindTaskByIdService findTaskByIdService;

    @BeforeEach
    void setUp() {
        this.closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        this.closeable.close();
    }

    @Test
    @DisplayName("Should throw an error if the task is not found")
    void shouldThrowError_whenTaskNotFound() {
        // Arrange
        String id = "random-id";
        Mockito.when(this.taskRepository.findById(id)).thenReturn(Optional.empty());

        // Act
        Throwable thrown = assertThrows(Exception.class, () -> this.findTaskByIdService.execute(id));

        // Assert
        assertEquals("Task not found", thrown.getMessage());
    }

    @Test
    @DisplayName("Should return a taskDto")
    void shouldReturnTaskDto() throws Exception {
        // Arrange
        String id = "random-id";
        Task task = new Task("title", "description");
        Mockito.when(this.taskRepository.findById(id)).thenReturn(Optional.of(task));

        // Act
        TaskDto result = this.findTaskByIdService.execute(id);

        // Assert
        assertNotNull(result);
    }
}