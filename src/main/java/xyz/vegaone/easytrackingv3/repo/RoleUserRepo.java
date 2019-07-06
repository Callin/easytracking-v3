package xyz.vegaone.easytrackingv3.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.vegaone.easytrackingv3.domain.RoleEntity;
import xyz.vegaone.easytrackingv3.domain.RoleUserEntity;

import java.util.List;

@Repository
public interface RoleUserRepo extends JpaRepository<RoleUserEntity, Long> {
    List<RoleUserEntity> findAllByUserId(Long userId);
}
