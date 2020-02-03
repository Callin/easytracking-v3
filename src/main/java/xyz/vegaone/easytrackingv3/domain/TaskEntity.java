package xyz.vegaone.easytrackingv3.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "task")
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private String status;

    @Column(name = "estimation")
    private Short estimation;

    @Column(name = "priority")
    private Short priority;

    @Column(name = "user_id")
    private Long userId;

    @ManyToOne (cascade = CascadeType.MERGE)
    @JoinColumn(name="sprint_id")
    private SprintEntity sprint;
}
