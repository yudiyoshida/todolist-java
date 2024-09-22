package com.matsugumayudi.todolist.task.usecases.findTaskById;

import com.matsugumayudi.todolist.exceptions.NotFoundException;
import com.matsugumayudi.todolist.task.dtos.TaskDto;
import com.matsugumayudi.todolist.task.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindTaskByIdService {
    @Autowired
    private TaskRepository taskRepository;

    public TaskDto execute(String id) {
        var task = this.taskRepository.findById(id);

        if (task.isEmpty()) {
            throw new NotFoundException("Task not found");
        }
        return new TaskDto(task.get());
    }
}
