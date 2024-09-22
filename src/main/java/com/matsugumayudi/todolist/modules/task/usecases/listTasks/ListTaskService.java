package com.matsugumayudi.todolist.modules.task.usecases.listTasks;

import com.matsugumayudi.todolist.modules.task.dtos.TaskDto;
import com.matsugumayudi.todolist.modules.task.entities.Task;
import com.matsugumayudi.todolist.modules.task.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListTaskService {
    @Autowired
    private TaskRepository taskRepository;

    public List<TaskDto> execute() {
        List<Task> tasks = this.taskRepository.findAll();

        return tasks.stream().map(TaskDto::new).toList();
    }
}
