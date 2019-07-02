package xyz.vegaone.easytrackingv3.security;

import com.github.dozermapper.core.Mapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import xyz.vegaone.easytrackingv3.domain.UserEntity;
import xyz.vegaone.easytrackingv3.dto.User;
import xyz.vegaone.easytrackingv3.repo.UserRepo;

@Service
public class EasytrackingUserDetailsService implements UserDetailsService {

    private UserRepo userRepository;

    private Mapper mapper;

    public EasytrackingUserDetailsService(UserRepo userRepository,
                                          Mapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null) {
            throw new UsernameNotFoundException(email);
        }

        return new EasytrackingUserPrincipal(mapper.map(userEntity, User.class));
    }
}
