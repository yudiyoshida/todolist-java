package com.matsugumayudi.todolist.task;

import com.matsugumayudi.todolist.task.dtos.TaskDto;
import com.matsugumayudi.todolist.task.usecases.createtask.CreateTaskService;
import com.matsugumayudi.todolist.task.usecases.createtask.dtos.CreateTaskInputDto;
import com.matsugumayudi.todolist.task.usecases.createtask.dtos.CreateTaskOutputDto;
import com.matsugumayudi.todolist.task.usecases.listtasks.ListTaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private CreateTaskService createTaskService;

    @Autowired
    private ListTaskService listTaskService;

    @PostMapping
    public ResponseEntity<CreateTaskOutputDto> createTask(@Valid @RequestBody CreateTaskInputDto body) {
        var result = this.createTaskService.execute(body);

        return ResponseEntity.status(201).body(result);
    }

    @GetMapping
    public ResponseEntity<List<TaskDto>> listTasks() {
        var result = this.listTaskService.execute();

        return ResponseEntity.ok(result);
    }
}
