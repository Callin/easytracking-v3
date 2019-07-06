package xyz.vegaone.easytrackingv3.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private Long id;
    private String firstName;
    private String secondName;
    private String email;
    private String password;

    @JsonIgnore
    private List<Task> taskList;

    private List<Role> roleList;
}
