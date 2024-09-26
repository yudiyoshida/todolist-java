package com.matsugumayudi.todolist.modules.task.usecases.createTask;

import com.matsugumayudi.todolist.modules.task.entities.Task;
import com.matsugumayudi.todolist.modules.task.repositories.TaskRepository;
import com.matsugumayudi.todolist.modules.task.usecases.createTask.dtos.CreateTaskInputDto;
import com.matsugumayudi.todolist.modules.task.usecases.createTask.dtos.CreateTaskOutputDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class CreateTaskServiceTest {
    private AutoCloseable closeable;

    @Mock
    private TaskRepository taskRepository;

    @Autowired
    @InjectMocks
    private CreateTaskService createTaskService;

    @BeforeEach
    void setUp() {
        this.closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        this.closeable.close();
    }

    @Test
    @DisplayName("Should create a task")
    void shouldCreateTask() {
        // Arrange
        CreateTaskInputDto input = new CreateTaskInputDto("Title", "Description");

        // Act
        CreateTaskOutputDto result = createTaskService.execute(input);

        // Assert
        assertNotNull(result);
        assertNotNull(result.id());
        verify(taskRepository, times(1)).save(any(Task.class));
    }
}