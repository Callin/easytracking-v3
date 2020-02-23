package xyz.vegaone.easytrackingv3.service;

import lombok.NonNull;
import org.springframework.stereotype.Service;
import xyz.vegaone.easytrackingv3.domain.ProjectEntity;
import xyz.vegaone.easytrackingv3.domain.SprintEntity;
import xyz.vegaone.easytrackingv3.domain.TaskEntity;
import xyz.vegaone.easytrackingv3.domain.UserEntity;
import xyz.vegaone.easytrackingv3.dto.Project;
import xyz.vegaone.easytrackingv3.dto.Sprint;
import xyz.vegaone.easytrackingv3.dto.Task;
import xyz.vegaone.easytrackingv3.dto.User;
import xyz.vegaone.easytrackingv3.repo.ProjectRepo;
import xyz.vegaone.easytrackingv3.repo.SprintRepo;
import xyz.vegaone.easytrackingv3.repo.TaskRepo;
import xyz.vegaone.easytrackingv3.repo.UserRepo;
import xyz.vegaone.easytrackingv3.util.MapperUtil;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    private ProjectRepo projectRepo;

    private MapperUtil mapperUtil;

    private SprintRepo sprintRepo;

    private TaskRepo taskRepo;

    public ProjectService(ProjectRepo projectRepo, MapperUtil mapperUtil, SprintRepo sprintRepo, TaskRepo taskRepo) {
        this.projectRepo = projectRepo;
        this.mapperUtil = mapperUtil;
        this.sprintRepo = sprintRepo;
        this.taskRepo = taskRepo;
    }

    public Project createProject(@NonNull Project project){
        ProjectEntity projectToSave = mapperUtil.map(project, ProjectEntity.class);
        return mapperUtil.map(projectRepo.save(projectToSave), Project.class);
    }

    public Project updateProject(@NonNull Project project){
        ProjectEntity projectToSave = mapperUtil.map(project, ProjectEntity.class);
        return mapperUtil.map(projectRepo.save(projectToSave), Project.class);
    }

    public void deleteProject(Long id){
        projectRepo.deleteById(id);
    }

    public Project getProject(Long id){
        Optional<ProjectEntity> projectOptional = projectRepo.findById(id);
        ProjectEntity projectEntity = projectOptional.orElseThrow();
        return mapperUtil.map(projectEntity, Project.class);
    }

    public List<Project> getAllProjects(){
        List<ProjectEntity> entityList = projectRepo.findAll();
        return mapperUtil.mapList(entityList, Project.class);
    }

    public List<Sprint> getAllSprintsForProject(Long projectId){
        List<SprintEntity> entityList = sprintRepo.findAllByProjectId(projectId);
        return mapperUtil.mapList(entityList, Sprint.class);
    }

    public List<Task> getAllTasksForProject(Long projectId){
        List<TaskEntity> entityList = taskRepo.findAllByProjectId(projectId);
        return mapperUtil.mapList(entityList, Task.class);
    }

    public List<User> getAllUsersForProject(Long id){
        Optional<ProjectEntity> projectOptional = projectRepo.findById(id);
        ProjectEntity projectEntity = projectOptional.orElseThrow();
        List<UserEntity> entityList = projectEntity.getUsers();
        return mapperUtil.mapList(entityList, User.class);
    }

}
