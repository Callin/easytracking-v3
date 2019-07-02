package xyz.vegaone.easytrackingv3.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Task {
    private Long id;
    private String title;
    private String description;
    private Short estimation;
    private Short priority;
    private User user;
    private Sprint sprint;
}
