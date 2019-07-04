package xyz.vegaone.easytrackingv3.service;

import com.github.dozermapper.core.Mapper;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import xyz.vegaone.easytrackingv3.domain.SprintEntity;
import xyz.vegaone.easytrackingv3.domain.TaskEntity;
import xyz.vegaone.easytrackingv3.domain.UserEntity;
import xyz.vegaone.easytrackingv3.dto.Sprint;
import xyz.vegaone.easytrackingv3.dto.Task;
import xyz.vegaone.easytrackingv3.dto.User;
import xyz.vegaone.easytrackingv3.repo.SprintRepo;
import xyz.vegaone.easytrackingv3.repo.TaskRepo;
import xyz.vegaone.easytrackingv3.repo.UserRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private TaskRepo taskRepo;

    private UserRepo userRepo;

    private SprintRepo sprintRepo;

    private Mapper mapper;

    public TaskService(TaskRepo taskRepo, UserRepo userRepo, SprintRepo sprintRepo, Mapper mapper) {
        this.taskRepo = taskRepo;
        this.userRepo = userRepo;
        this.sprintRepo = sprintRepo;
        this.mapper = mapper;
    }

    public Task createTask(@NonNull Task task) {
        TaskEntity taskToSave = mapper.map(task, TaskEntity.class);

        taskToSave.setSprintId(task.getSprint().getId());
        if (task.getUser() != null) {
            taskToSave.setUserId(task.getUser().getId());
        }

        return mapper.map(taskRepo.save(taskToSave), Task.class);
    }

    public Task updateTask(@NonNull Task task) {
        TaskEntity taskToSave = mapper.map(task, TaskEntity.class);

        taskToSave.setSprintId(task.getSprint().getId());
        if (task.getUser() != null) {
            taskToSave.setUserId(task.getUser().getId());
        }

        return mapper.map(taskRepo.save(taskToSave), Task.class);
    }

    public List<Task> getTasksForSprintId(Long sprintId) {
        List<TaskEntity> taskEntityList = taskRepo.findAllBySprintId(sprintId);

        List<Task> taskList = new ArrayList<>();

        for (TaskEntity taskEntity : taskEntityList) {
            Task task = mapper.map(taskEntity, Task.class);
            addUserAndSprint(taskEntity, task);
            taskList.add(task);
        }

        return taskList;
    }

    public Task getTask(Long id) {
        Optional<TaskEntity> taskOptional = taskRepo.findById(id);
        TaskEntity taskEntity = taskOptional.orElseThrow();
        Task task = mapper.map(taskEntity, Task.class);

        addUserAndSprint(taskEntity, task);

        return task;
    }

    private void addUserAndSprint(TaskEntity taskEntity, Task task) {
        if(taskEntity.getUserId() != null) {
            UserEntity userEntity = userRepo.findById(taskEntity.getUserId()).orElse(null);
            task.setUser(mapper.map(userEntity, User.class));
        }

        SprintEntity sprintEntity = sprintRepo.findById(taskEntity.getSprintId()).orElseThrow();
        task.setSprint(mapper.map(sprintEntity, Sprint.class));
    }

    public void deleteTask(Long id) {
        taskRepo.deleteById(id);
    }
}
