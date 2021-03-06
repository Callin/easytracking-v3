package xyz.vegaone.easytrackingv3.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.vegaone.easytrackingv3.domain.RoleEntity;

import java.util.List;

@Repository
public interface RoleRepo extends JpaRepository<RoleEntity, Long> {
    List<RoleEntity> findAllByIdIn(List<Long> idList);
}
