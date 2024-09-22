package com.matsugumayudi.todolist.task.usecases.createtask;

import com.matsugumayudi.todolist.task.entities.Task;
import com.matsugumayudi.todolist.task.repositories.TaskRepository;
import com.matsugumayudi.todolist.task.usecases.createtask.dtos.CreateTaskInputDto;
import com.matsugumayudi.todolist.task.usecases.createtask.dtos.CreateTaskOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateTaskService {
    @Autowired
    private TaskRepository taskRepository;

    public CreateTaskOutputDto execute(CreateTaskInputDto data) {
        Task task = new Task(data);

        this.taskRepository.save(task);

        return new CreateTaskOutputDto(task.getId());
    }
}
