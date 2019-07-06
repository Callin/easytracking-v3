package xyz.vegaone.easytrackingv3.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.vegaone.easytrackingv3.domain.SprintEntity;

@Repository
public interface SprintRepo extends JpaRepository<SprintEntity, Long> {
    SprintEntity findFirstByOrderBySprintNumberDesc();
}
