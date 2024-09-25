package com.matsugumayudi.todolist.modules.task.usecases.createTask;

import com.matsugumayudi.todolist.modules.task.entities.Task;
import com.matsugumayudi.todolist.modules.task.repositories.TaskRepository;
import com.matsugumayudi.todolist.modules.task.usecases.createTask.dtos.CreateTaskInputDto;
import com.matsugumayudi.todolist.modules.task.usecases.createTask.dtos.CreateTaskOutputDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class CreateTaskServiceTest {
    @Mock
    private TaskRepository taskRepository;

    @Autowired
    @InjectMocks
    private CreateTaskService createTaskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Should create a task")
    void execute() {
        CreateTaskInputDto input = new CreateTaskInputDto("Title", "Description");
        Task task = new Task(input.title(), input.description());

        CreateTaskOutputDto output = createTaskService.execute(input);

        assertNotNull(output);
        assertNotNull(output.id());
    }
}