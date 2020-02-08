package xyz.vegaone.easytrackingv3.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Backlog {
    List<Sprint> sprints;
    List<Task> backlogTasks;

    public Backlog() {
    }

    public Backlog(List<Sprint> sprints, List<Task> backlogTasks) {
        this.sprints = sprints;
        this.backlogTasks = backlogTasks;
    }
}
