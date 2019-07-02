package xyz.vegaone.easytrackingv3.service;

import com.github.dozermapper.core.Mapper;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import xyz.vegaone.easytrackingv3.domain.UserEntity;
import xyz.vegaone.easytrackingv3.domain.UserEntity;
import xyz.vegaone.easytrackingv3.dto.User;
import xyz.vegaone.easytrackingv3.dto.User;
import xyz.vegaone.easytrackingv3.repo.UserRepo;
import xyz.vegaone.easytrackingv3.repo.UserRepo;

import java.util.Optional;

@Service
public class UserService {
    private UserRepo userRepo;

    private Mapper mapper;

    public UserService(UserRepo userRepo, Mapper mapper) {
        this.userRepo = userRepo;
        this.mapper = mapper;
    }

    public User createUser(@NonNull User user) {
        return mapper.map(
                userRepo.save(
                        mapper.map(user, UserEntity.class)),
                User.class);
    }

    public User updateUser(@NonNull User user) {
        return mapper.map(
                userRepo.save(
                        mapper.map(user, UserEntity.class)),
                User.class);
    }

    public User getUser(Long id) {
        Optional<UserEntity> userOptional = userRepo.findById(id);
        return mapper.map(userOptional.orElseThrow(), User.class);
    }

    public User authenticate(User user) {
        UserEntity userEntity = userRepo.findByEmail(user.getEmail());
        return mapper.map(userEntity, User.class);
    }

    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }
}
