package com.matsugumayudi.todolist.modules.task.usecases.updateTask;

import com.matsugumayudi.todolist.shared.dtos.SuccessMessage;
import com.matsugumayudi.todolist.shared.exceptions.NotFoundException;
import com.matsugumayudi.todolist.modules.task.entities.Task;
import com.matsugumayudi.todolist.modules.task.repositories.TaskRepository;
import com.matsugumayudi.todolist.modules.task.usecases.updateTask.dtos.UpdateTaskInputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateTaskService {
    @Autowired
    private TaskRepository taskRepository;

    public SuccessMessage execute(String id, UpdateTaskInputDto data) {
        var result = this.taskRepository.findById(id);

        if (result.isEmpty()) {
            throw new NotFoundException("Task not found");
        }

        Task task = new Task(
            result.get().getId(),
            data.title(),
            data.description()
        );
        this.taskRepository.edit(task);

        return new SuccessMessage("Task updated");
    }
}
