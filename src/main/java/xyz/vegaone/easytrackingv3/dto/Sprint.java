package xyz.vegaone.easytrackingv3.dto;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Sprint {
    private Long id;
    private Integer sprintNumber;
    private Date startDate;
    private Date endDate;

    private List<Task> taskList;
}
