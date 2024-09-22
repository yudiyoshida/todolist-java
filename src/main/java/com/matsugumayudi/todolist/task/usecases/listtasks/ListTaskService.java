package com.matsugumayudi.todolist.task.usecases.listtasks;

import com.matsugumayudi.todolist.task.dtos.TaskDto;
import com.matsugumayudi.todolist.task.entities.Task;
import com.matsugumayudi.todolist.task.repositories.TaskRepository;
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
