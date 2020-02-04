package xyz.vegaone.easytrackingv3.service;

import lombok.NonNull;
import org.springframework.stereotype.Service;
import xyz.vegaone.easytrackingv3.domain.TaskEntity;
import xyz.vegaone.easytrackingv3.dto.Task;
import xyz.vegaone.easytrackingv3.repo.TaskRepo;
import xyz.vegaone.easytrackingv3.util.MapperUtil;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private TaskRepo taskRepo;

    private MapperUtil mapperUtil;

    public TaskService(TaskRepo taskRepo,
                       MapperUtil mapperUtil) {
        this.taskRepo = taskRepo;
        this.mapperUtil = mapperUtil;
    }

    public Task createTask(@NonNull Task task) {
        TaskEntity taskToSave = mapperUtil.map(task, TaskEntity.class);
        return mapperUtil.map(taskRepo.save(taskToSave), Task.class);
    }

    public Task updateTask(@NonNull Task task) {
        TaskEntity taskToSave = mapperUtil.map(task, TaskEntity.class);
        return mapperUtil.map(taskRepo.save(taskToSave), Task.class);
    }

    public List<Task> getTasksForSprintId(Long sprintId) {
        List<TaskEntity> taskEntityList = taskRepo.findAllBySprintId(sprintId);
        return mapperUtil.mapList(taskEntityList, Task.class);
    }

    public Task getTask(Long id) {
        Optional<TaskEntity> taskOptional = taskRepo.findById(id);
        TaskEntity taskEntity = taskOptional.orElseThrow();
        return mapperUtil.map(taskEntity, Task.class);
    }

    public List<Task> getAllTasks() {
        List<TaskEntity> entityList = taskRepo.findAll();
        return mapperUtil.mapList(entityList, Task.class);
    }

    public void deleteTask(Long id) {
        taskRepo.deleteById(id);
    }
}
