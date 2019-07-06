package xyz.vegaone.easytrackingv3.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.vegaone.easytrackingv3.domain.RoleEntity;

@Repository
public interface RoleRepo extends JpaRepository<RoleEntity, Long> {
}
