package xyz.vegaone.easytrackingv3.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.vegaone.easytrackingv3.domain.TaskEntity;

@Repository
public interface TaskRepo extends JpaRepository<TaskEntity, Long> {
}
