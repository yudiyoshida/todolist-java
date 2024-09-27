package com.matsugumayudi.todolist.modules.task.usecases.deleteTask;

import com.matsugumayudi.todolist.modules.task.repositories.TaskRepository;
import com.matsugumayudi.todolist.modules.task.repositories.TaskRepositoryJPA;
import com.matsugumayudi.todolist.shared.dtos.SuccessMessage;
import com.matsugumayudi.todolist.shared.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteTaskService {
//    @Autowired
//    private TaskRepository taskRepository;

    @Autowired
    private TaskRepositoryJPA taskRepositoryJPA;

    public SuccessMessage execute(String id) {
        var task = this.taskRepositoryJPA.findById(id);

        if (task.isEmpty()) {
            throw new NotFoundException("Task not found");
        }

        this.taskRepositoryJPA.delete(task.get());

        return new SuccessMessage("Task deleted");
    }
}
