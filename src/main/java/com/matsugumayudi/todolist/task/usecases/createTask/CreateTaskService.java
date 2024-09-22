package com.matsugumayudi.todolist.task.usecases.createTask;

import com.matsugumayudi.todolist.task.entities.Task;
import com.matsugumayudi.todolist.task.repositories.TaskRepository;
import com.matsugumayudi.todolist.task.usecases.createTask.dtos.CreateTaskInputDto;
import com.matsugumayudi.todolist.task.usecases.createTask.dtos.CreateTaskOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateTaskService {
    @Autowired
    private TaskRepository taskRepository;

    public CreateTaskOutputDto execute(CreateTaskInputDto data) {
        Task task = new Task(data.title(), data.description());

        this.taskRepository.save(task);

        return new CreateTaskOutputDto(task.getId());
    }
}
