package xyz.vegaone.easytrackingv3.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.vegaone.easytrackingv3.dto.Backlog;
import xyz.vegaone.easytrackingv3.service.BacklogService;

import static xyz.vegaone.easytrackingv3.controller.RestApiConstants.BASE_URL;

@RestController
@RequestMapping(BASE_URL + "backlog")
public class BacklogController {

    private BacklogService backlogService;

    public BacklogController(BacklogService backlogService) {
        this.backlogService = backlogService;
    }

    @GetMapping()
    public Backlog getBacklog() {
        return backlogService.getBacklog();
    }
}
