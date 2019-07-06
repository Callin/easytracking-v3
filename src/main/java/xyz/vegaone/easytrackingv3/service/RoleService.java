package xyz.vegaone.easytrackingv3.service;

import com.github.dozermapper.core.Mapper;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import xyz.vegaone.easytrackingv3.domain.RoleEntity;
import xyz.vegaone.easytrackingv3.dto.Role;
import xyz.vegaone.easytrackingv3.repo.RoleRepo;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {
    private RoleRepo roleRepo;
    private Mapper mapper;

    public RoleService(RoleRepo roleRepo, Mapper mapper) {
        this.roleRepo = roleRepo;
        this.mapper = mapper;
    }

    public Role createRole(@NonNull Role role) {
        RoleEntity roleToSave = mapper.map(role, RoleEntity.class);
        return mapper.map(roleRepo.save(roleToSave), Role.class);
    }

    public Role updateRole(@NonNull Role role) {
        RoleEntity roleToSave = mapper.map(role, RoleEntity.class);
        return mapper.map(roleRepo.save(roleToSave), Role.class);
    }

    public List<Role> getAllRoles() {
        List<RoleEntity> allRoles = roleRepo.findAll();

        List<Role> roleList = new ArrayList<>();
        for(RoleEntity roleEntity : allRoles) {
            roleList.add(mapper.map(roleEntity, Role.class));
        }

        return roleList;
    }

    public Role getRole(Long id) {
        return mapper.map(roleRepo.findById(id).orElseThrow(), Role.class);
    }

    public void deleteRole(Long id) {
        roleRepo.deleteById(id);
    }
}
