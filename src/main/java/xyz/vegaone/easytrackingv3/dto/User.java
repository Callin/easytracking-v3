package xyz.vegaone.easytrackingv3.dto;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class User {
    private Long id;
    private String firstName;
    private String secondName;
    private String email;
    private String password;

    private List<Task> taskList;

    private List<Role> roleList;
}
