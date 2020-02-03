package xyz.vegaone.easytrackingv3.service;

import lombok.NonNull;
import org.springframework.stereotype.Service;
import xyz.vegaone.easytrackingv3.domain.TaskEntity;
import xyz.vegaone.easytrackingv3.domain.UserEntity;
import xyz.vegaone.easytrackingv3.dto.Task;
import xyz.vegaone.easytrackingv3.dto.User;
import xyz.vegaone.easytrackingv3.repo.TaskRepo;
import xyz.vegaone.easytrackingv3.repo.UserRepo;
import xyz.vegaone.easytrackingv3.util.MapperUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private TaskRepo taskRepo;

    private UserRepo userRepo;

    private UserService userService;

    private MapperUtil mapperUtil;

    public TaskService(TaskRepo taskRepo,
                       UserRepo userRepo,
                       UserService userService,
                       MapperUtil mapperUtil) {
        this.taskRepo = taskRepo;
        this.userRepo = userRepo;
        this.userService = userService;
        this.mapperUtil = mapperUtil;
    }

    public Task createTask(@NonNull Task task) {
        TaskEntity taskToSave = mapperUtil.map(task, TaskEntity.class);

        if (task.getUser() != null) {
            taskToSave.setUserId(task.getUser().getId());
        }

        return mapperUtil.map(taskRepo.save(taskToSave), Task.class);
    }

    public Task updateTask(@NonNull Task task) {
        TaskEntity taskToSave = mapperUtil.map(task, TaskEntity.class);

        if (task.getUser() != null) {
            taskToSave.setUserId(task.getUser().getId());
        }

        return mapperUtil.map(taskRepo.save(taskToSave), Task.class);
    }

    public List<Task> getTasksForSprintId(Long sprintId) {
        List<TaskEntity> taskEntityList = taskRepo.findAllBySprintId(sprintId);

        List<Task> taskList = new ArrayList<>();

        for (TaskEntity taskEntity : taskEntityList) {
            Task task = mapperUtil.map(taskEntity, Task.class);
            addUserAndSprint(taskEntity, task);
            taskList.add(task);
        }

        return taskList;
    }

    public Task getTask(Long id) {
        Optional<TaskEntity> taskOptional = taskRepo.findById(id);
        TaskEntity taskEntity = taskOptional.orElseThrow();
        Task task = mapperUtil.map(taskEntity, Task.class);

        addUserAndSprint(taskEntity, task);

        return task;
    }

    public List<Task> getAllTasks() {
        List<TaskEntity> entityList = taskRepo.findAll();
        List<Task> taskList = mapperUtil.mapList(entityList, Task.class);

        List<User> userList = userService.getAllUsers();

        entityList.forEach(entity -> {
            userList
                    .stream()
                    .filter(user -> user.getId().equals(entity.getUserId()))
                    .findFirst()
                    .ifPresent(userDto -> taskList.stream()
                            .filter(task -> task.getId().equals(entity.getId()))
                            .findFirst()
                            .ifPresent(task -> task.setUser(userDto)));
        });

        return taskList;
    }

    private void addUserAndSprint(TaskEntity taskEntity, Task task) {
        if (taskEntity.getUserId() != null) {
            UserEntity userEntity = userRepo.findById(taskEntity.getUserId()).orElse(null);
            task.setUser(mapperUtil.map(userEntity, User.class));
        }
    }

    public void deleteTask(Long id) {
        taskRepo.deleteById(id);
    }
}
