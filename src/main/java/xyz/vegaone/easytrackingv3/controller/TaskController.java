package xyz.vegaone.easytrackingv3.controller;

import lombok.NonNull;
import org.springframework.web.bind.annotation.*;
import xyz.vegaone.easytrackingv3.dto.Task;
import xyz.vegaone.easytrackingv3.service.TaskService;

@RestController
@RequestMapping("/task")
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public Task createTask(@NonNull @RequestBody Task task) {
        return taskService.createTask(task);
    }

    @PutMapping
    public Task updateTask(@NonNull @RequestBody Task task) {
        return taskService.updateTask(task);
    }

    @GetMapping("{id}")
    public Task getTask(@PathVariable Long id) {
        return taskService.getTask(id);
    }

    @DeleteMapping("{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
}
