package xyz.vegaone.easytrackingv3.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@Entity
@Table(name = "task")
public class TaskEntity {
    @Id
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "estimation")
    private Short estimation;

    @Column(name = "priority")
    private Short priority;

    @Column(name = "sprint_id")
    private Long sprintId;

    @Column(name = "user_id")
    private Long userId;
}
