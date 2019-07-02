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
        return mapper.map(
                taskRepo.save(
                        mapper.map(task, TaskEntity.class)),
                Task.class);
    }

    public Task updateTask(@NonNull Task task) {
        return mapper.map(
                taskRepo.save(
                        mapper.map(task, TaskEntity.class)),
                Task.class);
    }

    public Task getTask(Long id) {
        Optional<TaskEntity> taskOptional = taskRepo.findById(id);
        TaskEntity taskEntity = taskOptional.orElseThrow();
        Task task = mapper.map(taskEntity, Task.class);

        UserEntity userEntity = userRepo.findById(taskEntity.getUserId()).orElse(null);
        task.setUser(mapper.map(userEntity, User.class));

        SprintEntity sprintEntity = sprintRepo.findById(taskEntity.getSprintId()).orElseThrow();
        task.setSprint(mapper.map(sprintEntity, Sprint.class));

        return task;
    }

    public void deleteTask(Long id) {
        taskRepo.deleteById(id);
    }
}
