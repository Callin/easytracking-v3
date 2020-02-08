package xyz.vegaone.easytrackingv3.service;

import org.springframework.stereotype.Service;
import xyz.vegaone.easytrackingv3.dto.Backlog;

@Service
public class BacklogService {

    private SprintService sprintService;

    private TaskService taskService;

    public BacklogService(SprintService sprintService, TaskService taskService) {
        this.sprintService = sprintService;
        this.taskService = taskService;
    }

    public Backlog getBacklog() {
        return new Backlog(sprintService.getAllSprints(), taskService.getAllTasksWithoutAnAssignedSprint());
    }
}
