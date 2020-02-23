package xyz.vegaone.easytrackingv3.controller;

import lombok.NonNull;
import org.springframework.web.bind.annotation.*;
import xyz.vegaone.easytrackingv3.dto.Project;
import xyz.vegaone.easytrackingv3.dto.Sprint;
import xyz.vegaone.easytrackingv3.dto.Task;
import xyz.vegaone.easytrackingv3.dto.User;
import xyz.vegaone.easytrackingv3.service.ProjectService;

import java.util.List;

import static xyz.vegaone.easytrackingv3.controller.RestApiConstants.BASE_URL;

@RestController
@RequestMapping(BASE_URL+"project")
public class ProjectController {

    private ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public Project createProject(@NonNull @RequestBody Project project){
        return projectService.createProject(project);
    }

    @PutMapping
    public Project updateProject(@NonNull @RequestBody Project project){
        return projectService.updateProject(project);
    }

    @GetMapping("{id}")
    public Project getProject(@PathVariable Long id){
        return projectService.getProject(id);
    }

    @GetMapping("all")
    public List<Project> getAllProject(){
        return projectService.getAllProjects();
    }

    @DeleteMapping("{id}")
    public void deleteProject(@PathVariable Long id){
        projectService.deleteProject(id);
    }

    @GetMapping("/sprint/{projectId}")
    public List<Sprint> getAllSprints(@PathVariable Long projectId){
        return projectService.getAllSprintsForProject(projectId);
    }

    @GetMapping("/tasks/{projectId}")
    public List<Task> getAllTasks(@PathVariable Long projectId){
        return projectService.getAllTasksForProject(projectId);
    }

    @GetMapping("/user/{projectId}")
    public List<User> getAllUsers(@PathVariable Long projectId){
        return projectService.getAllUsersForProject(projectId);
    }
}
