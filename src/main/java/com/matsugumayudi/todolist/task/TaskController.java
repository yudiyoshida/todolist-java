package com.matsugumayudi.todolist.task;

import com.matsugumayudi.todolist.dtos.SuccessMessage;
import com.matsugumayudi.todolist.task.dtos.TaskDto;
import com.matsugumayudi.todolist.task.usecases.createTask.CreateTaskService;
import com.matsugumayudi.todolist.task.usecases.createTask.dtos.CreateTaskInputDto;
import com.matsugumayudi.todolist.task.usecases.createTask.dtos.CreateTaskOutputDto;
import com.matsugumayudi.todolist.task.usecases.findTaskById.FindTaskByIdService;
import com.matsugumayudi.todolist.task.usecases.listTasks.ListTaskService;
import com.matsugumayudi.todolist.task.usecases.updateTask.UpdateTaskService;
import com.matsugumayudi.todolist.task.usecases.updateTask.dtos.UpdateTaskInputDto;
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

    @Autowired
    private FindTaskByIdService findTaskByIdService;

    @Autowired
    private UpdateTaskService updateTaskService;

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

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> findTaskById(@PathVariable String id) {
        var result = this.findTaskByIdService.execute(id);

        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessMessage> updateTask(@PathVariable String id, @Valid @RequestBody UpdateTaskInputDto body) {
        var result = this.updateTaskService.execute(id, body);

        return ResponseEntity.ok(result);
    }
}
