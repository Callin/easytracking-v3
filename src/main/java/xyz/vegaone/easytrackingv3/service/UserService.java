package xyz.vegaone.easytrackingv3.service;

import com.github.dozermapper.core.Mapper;
import lombok.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import xyz.vegaone.easytrackingv3.domain.UserEntity;
import xyz.vegaone.easytrackingv3.dto.User;
import xyz.vegaone.easytrackingv3.repo.UserRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepo userRepo;

    private Mapper mapper;

    private PasswordEncoder passwordEncoder;

    public UserService(UserRepo userRepo, Mapper mapper, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(@NonNull User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserEntity savedUser = userRepo.save(mapper.map(user, UserEntity.class));
        savedUser.setPassword(null);

        return mapper.map(savedUser, User.class);
    }

    public User updateUser(@NonNull User user) {
        UserEntity savedUser;

        if (!StringUtils.isEmpty(user.getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        } else {
            String userPassword = userRepo.getPassword(user.getId());
            user.setPassword(userPassword);
        }

        savedUser = userRepo.save(mapper.map(user, UserEntity.class));
        savedUser.setPassword(null);

        return mapper.map(savedUser, User.class);
    }

    public User getUser(Long id) {
        Optional<UserEntity> userOptional = userRepo.findById(id);
        User user = mapper.map(userOptional.orElseThrow(), User.class);
        user.setPassword(null);
        return user;
    }

    public List<User> getAllUsers() {
        List<UserEntity> userEntityList = userRepo.findAll();
        List<User> userList = new ArrayList<>();

        for (UserEntity userEntity : userEntityList) {
            userEntity.setPassword(null);
            userList.add(mapper.map(userEntity, User.class));
        }

        return userList;
    }

    public User authenticate(User user) {
        UserEntity userEntity = userRepo.findByEmail(user.getEmail());
        userEntity.setPassword(null);
        return mapper.map(userEntity, User.class);
    }

    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }
}
