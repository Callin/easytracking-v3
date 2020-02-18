package xyz.vegaone.easytrackingv3.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import xyz.vegaone.easytrackingv3.domain.SprintEntity;
import xyz.vegaone.easytrackingv3.domain.TaskEntity;
import xyz.vegaone.easytrackingv3.domain.UserEntity;

import java.util.List;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Project {

    private Long id;
    private String projectName;
    private String projectDescription;
    private List<SprintEntity> sprints;
    private List<TaskEntity> tasks;
    private List<UserEntity> users;
}
