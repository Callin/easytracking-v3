package xyz.vegaone.easytrackingv3.controller;

import lombok.NonNull;
import org.springframework.web.bind.annotation.*;
import xyz.vegaone.easytrackingv3.dto.Task;
import xyz.vegaone.easytrackingv3.service.TaskService;

import java.util.List;

import static xyz.vegaone.easytrackingv3.controller.RestApiConstants.BASE_URL;

@RestController
@RequestMapping(BASE_URL + "task")
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

    @GetMapping("/sprint/{sprintId}")
    public List<Task> getTasksForSprintId(@PathVariable Long sprintId) {
        return taskService.getTasksForSprintId(sprintId);
    }

    @GetMapping("all")
    public List<Task> getAll() {
        return taskService.getAllTasks();
    }

    @DeleteMapping("{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
}
