package com.matsugumayudi.todolist.task;

import com.matsugumayudi.todolist.task.usecases.CreateTaskService;
import com.matsugumayudi.todolist.task.usecases.dtos.CreateTaskInputDto;
import com.matsugumayudi.todolist.task.usecases.dtos.CreateTaskOutputDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private CreateTaskService createTaskService;

    @PostMapping
    public ResponseEntity<CreateTaskOutputDto> createTask(@Valid @RequestBody CreateTaskInputDto body) {
        var result = this.createTaskService.execute(body);

        return ResponseEntity.status(201).body(result);
    }
}
