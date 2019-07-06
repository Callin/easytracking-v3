package xyz.vegaone.easytrackingv3.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
public class Role {
    private Long id;
    private String roleName;
}
