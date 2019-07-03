package xyz.vegaone.easytrackingv3.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.vegaone.easytrackingv3.domain.TaskEntity;

import java.util.List;

@Repository
public interface TaskRepo extends JpaRepository<TaskEntity, Long> {
    List<TaskEntity> findAllBySprintId(Long sprintId);
}
