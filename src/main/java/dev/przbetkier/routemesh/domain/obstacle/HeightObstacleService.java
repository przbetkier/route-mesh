package dev.przbetkier.routemesh.domain.obstacle;

import dev.przbetkier.routemesh.api.request.HeightObstacleRequest;
import dev.przbetkier.routemesh.api.response.ObstacleResponse;
import dev.przbetkier.routemesh.domain.common.DomainException;
import dev.przbetkier.routemesh.domain.obstacle.height.HeightObstacle;
import dev.przbetkier.routemesh.domain.obstacle.height.HeightObstacleRepository;
import dev.przbetkier.routemesh.domain.road.RoadsService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static dev.przbetkier.routemesh.domain.obstacle.ObstacleType.fromString;

@Service
class HeightObstacleService {

    private final HeightObstacleRepository repository;
    private final RoadsService roadsService;

    HeightObstacleService(HeightObstacleRepository repository, RoadsService roadsService) {
        this.repository = repository;
        this.roadsService = roadsService;
    }

    List<HeightObstacle> getAll() {
        return repository.findAll();
    }

    Optional<HeightObstacle> getById(Long id) {
        return repository.findById(id);
    }

    ObstacleResponse save(HeightObstacleRequest request) {
        var obstacleToSave = new HeightObstacle(new Obstacle(request.getName(),
                                                             request.getCity(),
                                                             request.getLatitude(),
                                                             request.getLongitude(),
                                                             request.isImmovable(),
                                                             request.getMilestone(),
                                                             request.getUrl(),
                                                             request.getComment(),
                                                             fromString(request.getType()),
                                                             ObstacleSubtype.fromString(request.getSubtype()),
                                                             roadsService.getById(request.getRoadId())
                                                                     .orElseThrow(() -> new DomainException(
                                                                             "Could not find road",
                                                                             HttpStatus.UNPROCESSABLE_ENTITY))),
                                                request.getLimit(),
                                                request.getProfile(),
                                                request.getRange());
        Obstacle obstacle = repository.save(obstacleToSave);
        return ObstacleResponse.fromObstacle(obstacle);
    }
}
