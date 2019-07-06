package xyz.vegaone.easytrackingv3.controller;

import lombok.NonNull;
import org.springframework.web.bind.annotation.*;
import xyz.vegaone.easytrackingv3.dto.Role;
import xyz.vegaone.easytrackingv3.service.RoleService;

import java.util.List;

import static xyz.vegaone.easytrackingv3.controller.RestApiConstants.BASE_URL;

@RestController
@RequestMapping(BASE_URL + "role")
public class RoleController {

    private RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public Role createRole(@NonNull @RequestBody Role role) {
        return roleService.createRole(role);
    }

    @PutMapping
    public Role updateRole(@NonNull @RequestBody Role role) {
        return roleService.updateRole(role);
    }

    @GetMapping("{id}")
    public Role getRole(@PathVariable Long id) {
        return roleService.getRole(id);
    }

    @GetMapping("all")
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @DeleteMapping("{id}")
    public void deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
    }
}
