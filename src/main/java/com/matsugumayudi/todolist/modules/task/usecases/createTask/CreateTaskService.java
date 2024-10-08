package com.matsugumayudi.todolist.modules.task.usecases.createTask;

import com.matsugumayudi.todolist.modules.task.entities.Task;
import com.matsugumayudi.todolist.modules.task.repositories.TaskRepository;
import com.matsugumayudi.todolist.modules.task.repositories.TaskRepositoryJPA;
import com.matsugumayudi.todolist.modules.task.usecases.createTask.dtos.CreateTaskInputDto;
import com.matsugumayudi.todolist.modules.task.usecases.createTask.dtos.CreateTaskOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateTaskService {
//    @Autowired
//    private TaskRepository taskRepository;

    @Autowired
    private TaskRepositoryJPA taskRepositoryJPA;

    public CreateTaskOutputDto execute(CreateTaskInputDto data) {
        Task task = new Task(data.title(), data.description());

        this.taskRepositoryJPA.save(task);

        return new CreateTaskOutputDto(task.getId());
    }
}
