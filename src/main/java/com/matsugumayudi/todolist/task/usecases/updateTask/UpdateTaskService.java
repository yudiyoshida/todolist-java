package com.matsugumayudi.todolist.task.usecases.updateTask;

import com.matsugumayudi.todolist.dtos.SuccessMessage;
import com.matsugumayudi.todolist.exceptions.NotFoundException;
import com.matsugumayudi.todolist.task.entities.Task;
import com.matsugumayudi.todolist.task.repositories.TaskRepository;
import com.matsugumayudi.todolist.task.usecases.updateTask.dtos.UpdateTaskInputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateTaskService {
    @Autowired
    private TaskRepository taskRepository;

    public SuccessMessage execute(String id, UpdateTaskInputDto data) {
        Optional<Task> result = this.taskRepository.findById(id);

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
