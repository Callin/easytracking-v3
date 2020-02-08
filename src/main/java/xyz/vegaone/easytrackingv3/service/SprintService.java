package xyz.vegaone.easytrackingv3.service;

import lombok.NonNull;
import org.springframework.stereotype.Service;
import xyz.vegaone.easytrackingv3.domain.SprintEntity;
import xyz.vegaone.easytrackingv3.dto.Sprint;
import xyz.vegaone.easytrackingv3.repo.SprintRepo;
import xyz.vegaone.easytrackingv3.util.MapperUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class SprintService {
    private SprintRepo sprintRepo;

    private MapperUtil mapperUtil;

    public SprintService(SprintRepo sprintRepo, MapperUtil mapperUtil) {
        this.sprintRepo = sprintRepo;
        this.mapperUtil = mapperUtil;
    }

    public Sprint createSprint(@NonNull Sprint sprint) {
        SprintEntity sprintEntity = sprintRepo.findFirstByOrderBySprintNumberDesc();
        sprint.setSprintNumber(sprintEntity == null ? 1 : sprintEntity.getSprintNumber() + 1);

        return mapperUtil.map(
                sprintRepo.save(
                        mapperUtil.map(sprint, SprintEntity.class)),
                Sprint.class);
    }

    public Sprint updateSprint(@NonNull Sprint sprint) {
        return mapperUtil.map(
                sprintRepo.save(
                        mapperUtil.map(sprint, SprintEntity.class)),
                Sprint.class);
    }

    public Sprint getSprint(Long id) {
        Optional<SprintEntity> sprintOptional = sprintRepo.findById(id);
        return mapperUtil.map(sprintOptional.orElseThrow(), Sprint.class);
    }


    public List<Sprint> getAllSprintsSorted() {
        List<Sprint> sprintList = getAllSprints();
        sprintList.sort(Comparator.comparing(Sprint::getSprintNumber).reversed());

        return sprintList;
    }
    public List<Sprint> getAllSprints() {
        List<SprintEntity> sprintEntityList = sprintRepo.findAll();

        List<Sprint> sprintList = new ArrayList<>();

        for (SprintEntity sprintEntity : sprintEntityList) {
            sprintList.add(mapperUtil.map(sprintEntity, Sprint.class));
        }

        return sprintList;
    }

    public void deleteSprint(Long id) {
        sprintRepo.deleteById(id);
    }
}
