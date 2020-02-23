package xyz.vegaone.easytrackingv3.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.vegaone.easytrackingv3.domain.ProjectEntity;

@Repository
public interface ProjectRepo extends JpaRepository<ProjectEntity, Long> {

//    List<User> findAllByProjectId(Long projectId);
    }
