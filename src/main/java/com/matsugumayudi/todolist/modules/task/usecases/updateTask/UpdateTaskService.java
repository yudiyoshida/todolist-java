package com.matsugumayudi.todolist.modules.task.usecases.updateTask;

import com.matsugumayudi.todolist.modules.task.repositories.TaskRepositoryJPA;
import com.matsugumayudi.todolist.shared.dtos.SuccessMessage;
import com.matsugumayudi.todolist.shared.exceptions.NotFoundException;
import com.matsugumayudi.todolist.modules.task.entities.Task;
import com.matsugumayudi.todolist.modules.task.repositories.TaskRepository;
import com.matsugumayudi.todolist.modules.task.usecases.updateTask.dtos.UpdateTaskInputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateTaskService {
//    @Autowired
//    private TaskRepository taskRepository;

    @Autowired
    private TaskRepositoryJPA taskRepositoryJPA;

    public SuccessMessage execute(String id, UpdateTaskInputDto data) {
        var result = this.taskRepositoryJPA.findById(id);

        if (result.isEmpty()) {
            throw new NotFoundException("Task not found");
        }

        Task task = new Task(
            result.get().getId(),
            data.title(),
            data.description() != null ? data.description() : result.get().getDescription(),
            result.get().getCompleted()
        );
        this.taskRepositoryJPA.save(task);

        return new SuccessMessage("Task updated");
    }
}
