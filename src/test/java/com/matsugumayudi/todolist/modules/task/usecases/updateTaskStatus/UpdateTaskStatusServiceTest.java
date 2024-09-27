package com.matsugumayudi.todolist.modules.task.usecases.updateTaskStatus;

import com.matsugumayudi.todolist.modules.task.entities.Task;
import com.matsugumayudi.todolist.modules.task.repositories.TaskRepositoryJPA;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UpdateTaskStatusServiceTest {
    private AutoCloseable closeable;

    @Mock
    private TaskRepositoryJPA taskRepository;

    @InjectMocks
    private UpdateTaskStatusService updateTaskStatusService;

    @BeforeEach
    public void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void close() throws Exception {
        closeable.close();
    }

    @Test
    @DisplayName("Should throw an error when task is not found")
    public void shouldThrowAnErrorWhenTaskIsNotFound() {
        // Given
        String taskId = "task-id";
        Mockito.when(this.taskRepository.findById(taskId)).thenReturn(Optional.empty());

        // When
        Exception exception = assertThrows(Exception.class, () -> updateTaskStatusService.execute(taskId));

        // Then
        assertEquals("Task not found", exception.getMessage());
    }

    @Test
    @DisplayName("Should update task status")
    public void shouldUpdateTaskStatus() {
        // Given
        String taskId = "task-id";
        var task = new Task("task-id", "Task title", "Task description", false);
        Mockito.when(this.taskRepository.findById(taskId)).thenReturn(Optional.of(task));

        // When
        var result = updateTaskStatusService.execute(taskId);

        // Then
        assertEquals("Task status updated", result.message());
    }

    @Test
    @DisplayName("Should call save method from task repository with correct status")
    public void shouldCallSaveMethodFromTaskRepositoryWithCorrectStatus() {
        // Given
        String taskId = "task-id";
        var task = new Task("task-id", "Task title", "Task description", false);
        Mockito.when(this.taskRepository.findById(taskId)).thenReturn(Optional.of(task));

        // When
        ArgumentCaptor<Task> taskCaptor = ArgumentCaptor.forClass(Task.class);
        updateTaskStatusService.execute(taskId);

        // Then
        Mockito.verify(this.taskRepository).save(taskCaptor.capture());
        assertTrue(taskCaptor.getValue().getCompleted());
    }
}