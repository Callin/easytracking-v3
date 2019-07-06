package xyz.vegaone.easytrackingv3.service;

import lombok.NonNull;
import org.springframework.stereotype.Service;
import xyz.vegaone.easytrackingv3.domain.RoleEntity;
import xyz.vegaone.easytrackingv3.dto.Role;
import xyz.vegaone.easytrackingv3.repo.RoleRepo;
import xyz.vegaone.easytrackingv3.util.MapperUtil;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {
    private RoleRepo roleRepo;
    private MapperUtil mapperUtil;

    public RoleService(RoleRepo roleRepo, MapperUtil mapperUtil) {
        this.roleRepo = roleRepo;
        this.mapperUtil = mapperUtil;
    }

    public Role createRole(@NonNull Role role) {
        RoleEntity roleToSave = mapperUtil.map(role, RoleEntity.class);
        return mapperUtil.map(roleRepo.save(roleToSave), Role.class);
    }

    public Role updateRole(@NonNull Role role) {
        RoleEntity roleToSave = mapperUtil.map(role, RoleEntity.class);
        return mapperUtil.map(roleRepo.save(roleToSave), Role.class);
    }

    public List<Role> getAllRoles() {
        List<RoleEntity> allRoles = roleRepo.findAll();

        List<Role> roleList = new ArrayList<>();
        for (RoleEntity roleEntity : allRoles) {
            roleList.add(mapperUtil.map(roleEntity, Role.class));
        }

        return roleList;
    }

    public Role getRole(Long id) {
        return mapperUtil.map(roleRepo.findById(id).orElseThrow(), Role.class);
    }

    public void deleteRole(Long id) {
        roleRepo.deleteById(id);
    }
}
