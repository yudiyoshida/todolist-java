package com.matsugumayudi.todolist.modules.task.usecases.deleteTask;

import com.matsugumayudi.todolist.modules.task.repositories.TaskRepository;
import com.matsugumayudi.todolist.shared.dtos.SuccessMessage;
import com.matsugumayudi.todolist.shared.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteTaskService {
    @Autowired
    private TaskRepository taskRepository;

    public SuccessMessage execute(String id) {
        var task = this.taskRepository.findById(id);

        if (task.isEmpty()) {
            throw new NotFoundException("Task not found");
        }

        this.taskRepository.delete(task.get().getId());

        return new SuccessMessage("Task deleted");
    }
}
