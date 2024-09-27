package com.matsugumayudi.todolist.modules.task;

import com.matsugumayudi.todolist.modules.task.usecases.deleteTask.DeleteTaskService;
import com.matsugumayudi.todolist.modules.task.usecases.updateTaskStatus.UpdateTaskStatusService;
import com.matsugumayudi.todolist.shared.dtos.SuccessMessage;
import com.matsugumayudi.todolist.modules.task.dtos.TaskDto;
import com.matsugumayudi.todolist.modules.task.usecases.createTask.CreateTaskService;
import com.matsugumayudi.todolist.modules.task.usecases.createTask.dtos.CreateTaskInputDto;
import com.matsugumayudi.todolist.modules.task.usecases.createTask.dtos.CreateTaskOutputDto;
import com.matsugumayudi.todolist.modules.task.usecases.findTaskById.FindTaskByIdService;
import com.matsugumayudi.todolist.modules.task.usecases.listTasks.ListTaskService;
import com.matsugumayudi.todolist.modules.task.usecases.updateTask.UpdateTaskService;
import com.matsugumayudi.todolist.modules.task.usecases.updateTask.dtos.UpdateTaskInputDto;
import io.swagger.v3.oas.annotations.Operation;
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

    @Autowired
    private DeleteTaskService deleteTaskService;

    @Autowired
    private UpdateTaskStatusService updateTaskStatusService;

    @PostMapping
    @Operation(summary = "Create a new task", tags = { "Task" })
    public ResponseEntity<CreateTaskOutputDto> createTask(@Valid @RequestBody CreateTaskInputDto body) {
        var result = this.createTaskService.execute(body);

        return ResponseEntity.status(201).body(result);
    }

    @GetMapping
    @Operation(summary = "List all tasks", tags = { "Task" })
    public ResponseEntity<List<TaskDto>> listTasks() {
        var result = this.listTaskService.execute();

        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find a task by id", tags = { "Task" })
    public ResponseEntity<TaskDto> findTaskById(@PathVariable String id) {
        var result = this.findTaskByIdService.execute(id);

        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a task", tags = { "Task" })
    public ResponseEntity<SuccessMessage> updateTask(@PathVariable String id, @Valid @RequestBody UpdateTaskInputDto body) {
        var result = this.updateTaskService.execute(id, body);

        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a task", tags = { "Task" })
    public ResponseEntity<SuccessMessage> deleteTask(@PathVariable String id) {
        var result = this.deleteTaskService.execute(id);

        return ResponseEntity.ok(result);
    }

    @PatchMapping("/{id}/status")
    @Operation(summary = "Update a task status", tags = { "Task" })
    public ResponseEntity<SuccessMessage> updateTaskStatus(@PathVariable String id) {
        var result = this.updateTaskStatusService.execute(id);

        return ResponseEntity.ok(result);
    }
}
