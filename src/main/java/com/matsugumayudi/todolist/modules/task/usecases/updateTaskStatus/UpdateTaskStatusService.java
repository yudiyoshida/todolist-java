package com.matsugumayudi.todolist.modules.task.usecases.updateTaskStatus;

import com.matsugumayudi.todolist.modules.task.entities.Task;
import com.matsugumayudi.todolist.modules.task.repositories.TaskRepositoryJPA;
import com.matsugumayudi.todolist.shared.dtos.SuccessMessage;
import com.matsugumayudi.todolist.shared.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateTaskStatusService {
    @Autowired
    private TaskRepositoryJPA taskRepositoryJPA;

    public SuccessMessage execute(String id) {
        var result = this.taskRepositoryJPA.findById(id);

        if (result.isEmpty()) {
            throw new NotFoundException("Task not found");
        }

        var task = new Task(
            result.get().getId(),
            result.get().getTitle(),
            result.get().getDescription(),
            !result.get().getCompleted()
        );
        this.taskRepositoryJPA.save(task);

        return new SuccessMessage("Task status updated");
    }
}
