package xyz.vegaone.easytrackingv3.controller;

import lombok.NonNull;
import org.springframework.web.bind.annotation.*;
import xyz.vegaone.easytrackingv3.dto.Sprint;
import xyz.vegaone.easytrackingv3.service.SprintService;

@RestController
@RequestMapping("/sprint")
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

    @DeleteMapping("{id}")
    public void deleteSprint(@PathVariable Long id) {
        sprintService.deleteSprint(id);
    }
}
