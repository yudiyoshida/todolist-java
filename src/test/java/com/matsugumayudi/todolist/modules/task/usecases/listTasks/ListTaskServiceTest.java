package com.matsugumayudi.todolist.modules.task.usecases.listTasks;

import com.matsugumayudi.todolist.modules.task.entities.Task;
import com.matsugumayudi.todolist.modules.task.repositories.TaskRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ListTaskServiceTest {
    private AutoCloseable closeable;

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private ListTaskService listTaskService;

    @BeforeEach
    void setUp() {
        this.closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        this.closeable.close();
    }

    @Test
    @DisplayName("Should return a list of tasks")
    void shouldReturnListOfTasks() {
        // Given
        List<Task> tasks = new ArrayList<>();
        Mockito.when(this.taskRepository.findAll()).thenReturn(tasks);

        // When
        var result = this.listTaskService.execute();

        // Then
        assertTrue(result.isEmpty());
    }
}