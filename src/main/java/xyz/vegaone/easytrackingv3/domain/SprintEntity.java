package xyz.vegaone.easytrackingv3.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@Table(name = "sprint")
public class SprintEntity {
    @Id
    private Long id;

    @Column(name = "sprint_number")
    private Integer sprintNumber;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Short endDate;
}
