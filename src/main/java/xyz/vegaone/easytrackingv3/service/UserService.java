package xyz.vegaone.easytrackingv3.service;

import lombok.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import xyz.vegaone.easytrackingv3.domain.RoleUserEntity;
import xyz.vegaone.easytrackingv3.domain.UserEntity;
import xyz.vegaone.easytrackingv3.dto.Role;
import xyz.vegaone.easytrackingv3.dto.User;
import xyz.vegaone.easytrackingv3.repo.RoleRepo;
import xyz.vegaone.easytrackingv3.repo.RoleUserRepo;
import xyz.vegaone.easytrackingv3.repo.UserRepo;
import xyz.vegaone.easytrackingv3.util.MapperUtil;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private UserRepo userRepo;

    private PasswordEncoder passwordEncoder;

    private RoleUserRepo roleUserRepo;

    private RoleRepo roleRepo;

    private MapperUtil mapperUtil;

    public UserService(UserRepo userRepo,
                       PasswordEncoder passwordEncoder,
                       RoleUserRepo roleUserRepo,
                       RoleRepo roleRepo,
                       MapperUtil mapperUtil) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.roleUserRepo = roleUserRepo;
        this.roleRepo = roleRepo;
        this.mapperUtil = mapperUtil;
    }

    public User createUser(@NonNull User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserEntity savedUser = userRepo.save(mapperUtil.map(user, UserEntity.class));
        savedUser.setPassword(null);

        return mapperUtil.map(savedUser, User.class);
    }

    public User updateUser(@NonNull User user) {
        UserEntity savedUser;

        if (!StringUtils.isEmpty(user.getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        } else {
            String userPassword = userRepo.getPassword(user.getId());
            user.setPassword(userPassword);
        }

        savedUser = userRepo.save(mapperUtil.map(user, UserEntity.class));
        savedUser.setPassword(null);

        return mapperUtil.map(savedUser, User.class);
    }

    public User getUser(Long id) {
        Optional<UserEntity> userOptional = userRepo.findById(id);
        User user = mapperUtil.map(userOptional.orElseThrow(), User.class);

        List<RoleUserEntity> roleUserEntityList = roleUserRepo.findAllByUserId(id);
        List<Long> roleIdList = roleUserEntityList.stream()
                .map(RoleUserEntity::getId)
                .collect(Collectors.toList());

        user.setRoleList(mapperUtil.mapList(roleRepo.findAllByIdIn(roleIdList), Role.class));

        user.setPassword(null);

        return user;
    }

    public List<User> getAllUsers() {
        List<UserEntity> userEntityList = userRepo.findAll();
        return mapperUtil.mapList(userEntityList, User.class);
    }

    public User authenticate(User user) {
        UserEntity userEntity = userRepo.findByEmail(user.getEmail());
        User authenticatedUser = mapperUtil.map(userEntity, User.class);

        List<RoleUserEntity> roleUserEntityList = roleUserRepo.findAllByUserId(authenticatedUser.getId());
        List<Long> roleIdList = roleUserEntityList.stream()
                .map(RoleUserEntity::getRoleId)
                .collect(Collectors.toList());

        authenticatedUser.setRoleList(mapperUtil.mapList(roleRepo.findAllByIdIn(roleIdList), Role.class));

        authenticatedUser.setPassword(null);

        return authenticatedUser;
    }

    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }
}
