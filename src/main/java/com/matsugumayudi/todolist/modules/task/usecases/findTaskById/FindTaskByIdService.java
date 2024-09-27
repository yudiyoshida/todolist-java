package com.matsugumayudi.todolist.modules.task.usecases.findTaskById;

import com.matsugumayudi.todolist.modules.task.repositories.TaskRepositoryJPA;
import com.matsugumayudi.todolist.shared.exceptions.NotFoundException;
import com.matsugumayudi.todolist.modules.task.dtos.TaskDto;
import com.matsugumayudi.todolist.modules.task.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FindTaskByIdService {
//    @Autowired
//    private TaskRepository taskRepository;

    @Autowired
    private TaskRepositoryJPA taskRepositoryJPA;

    public TaskDto execute(String id) {
        var task = this.taskRepositoryJPA.findById(id);

        if (task.isEmpty()) {
            throw new NotFoundException("Task not found");
        }
        return new TaskDto(task.get());
    }
}
