package xyz.vegaone.easytrackingv3.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Sprint {
    private Long id;
    private Integer sprintNumber;
    private Date startDate;
    private Short endDate;

    @JsonIgnore
    private List<Task> taskList;
}
