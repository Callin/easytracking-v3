package xyz.vegaone.easytrackingv3.service;

import com.github.dozermapper.core.Mapper;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import xyz.vegaone.easytrackingv3.domain.SprintEntity;
import xyz.vegaone.easytrackingv3.dto.Sprint;
import xyz.vegaone.easytrackingv3.repo.SprintRepo;

import java.util.Optional;

@Service
public class SprintService {
    private SprintRepo sprintRepo;

    private Mapper mapper;

    public SprintService(SprintRepo sprintRepo, Mapper mapper) {
        this.sprintRepo = sprintRepo;
        this.mapper = mapper;
    }

    public Sprint createSprint(@NonNull Sprint sprint) {
        SprintEntity sprintEntity = sprintRepo.findFirstByOrderBySprintNumber();
        sprint.setSprintNumber(sprintEntity.getSprintNumber() + 1);

        return mapper.map(
                sprintRepo.save(
                        mapper.map(sprint, SprintEntity.class)),
                Sprint.class);
    }

    public Sprint updateSprint(@NonNull Sprint sprint) {
        return mapper.map(
                sprintRepo.save(
                        mapper.map(sprint, SprintEntity.class)),
                Sprint.class);
    }

    public Sprint getSprint(Long id) {
        Optional<SprintEntity> sprintOptional = sprintRepo.findById(id);
        return mapper.map(sprintOptional.orElseThrow(), Sprint.class);
    }

    public void deleteSprint(Long id) {
        sprintRepo.deleteById(id);
    }
}
