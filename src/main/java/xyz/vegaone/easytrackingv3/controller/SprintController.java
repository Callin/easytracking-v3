package xyz.vegaone.easytrackingv3.controller;

import lombok.NonNull;
import org.springframework.web.bind.annotation.*;
import xyz.vegaone.easytrackingv3.dto.Sprint;
import xyz.vegaone.easytrackingv3.service.SprintService;

import java.util.List;

import static xyz.vegaone.easytrackingv3.controller.RestApiConstants.BASE_URL;

@RestController
@RequestMapping(BASE_URL + "sprint")
public class SprintController {

    private SprintService sprintService;

    public SprintController(SprintService sprintService) {
        this.sprintService = sprintService;
    }

    @PostMapping
    public Sprint createSprint(@NonNull @RequestBody Sprint sprint) {
        return sprintService.createSprint(sprint);
    }

    @PutMapping
    public Sprint updateSprint(@NonNull @RequestBody Sprint sprint) {
        return sprintService.updateSprint(sprint);
    }

    @GetMapping("{id}")
    public Sprint getSprint(@PathVariable Long id) {
        return sprintService.getSprint(id);
    }

    @GetMapping("all")
    public List<Sprint> getAllSprintsSorted() {
        return sprintService.getAllSprintsSorted();
    }

    @DeleteMapping("{id}")
    public void deleteSprint(@PathVariable Long id) {
        sprintService.deleteSprint(id);
    }
}
