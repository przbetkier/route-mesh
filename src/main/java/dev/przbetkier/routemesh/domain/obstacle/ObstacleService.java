package dev.przbetkier.routemesh.domain.obstacle;

import dev.przbetkier.routemesh.api.request.ObstacleRequest;
import dev.przbetkier.routemesh.api.response.ObstacleResponse;
import dev.przbetkier.routemesh.domain.common.DomainException;
import dev.przbetkier.routemesh.domain.common.EntityNotFoundException;
import dev.przbetkier.routemesh.domain.road.RoadsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@Service
public class ObstacleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ObstacleService.class);

    private final ObstacleRepository obstacleRepository;
    private final RoadsService roadsService;

    public ObstacleService(ObstacleRepository obstacleRepository, RoadsService roadsService) {
        this.obstacleRepository = obstacleRepository;
        this.roadsService = roadsService;
    }

    public List<ObstacleResponse> getAll() {
        return obstacleRepository.findAll().stream().map(ObstacleResponse::fromObstacle).collect(Collectors.toList());
    }

    public ObstacleResponse getById(Long obstacleId) {
        return obstacleRepository.findById(obstacleId)
                .map(ObstacleResponse::fromObstacle)
                .orElseThrow(() -> new EntityNotFoundException("obstacle", obstacleId));
    }

    public void deleteById(Long obstacleId) {
        obstacleRepository.deleteById(obstacleId);
        LOGGER.info("Removed obstacle: [{}]", obstacleId);
    }

    public ObstacleResponse saveFromRequest(ObstacleRequest request) {
        var obstacleToSave = new Obstacle(request.getName(),
                                          request.getCity(),
                                          request.getLatitude(),
                                          request.getLongitude(),
                                          request.isImmovable(),
                                          request.getMilestone(),
                                          request.getUrl(),
                                          request.getComment(),
                                          request.getObstructions().getHeight(),
                                          roadsService.getById(request.getRoadId())
                                                  .orElseThrow(() -> new DomainException("Could not find road",
                                                                                         UNPROCESSABLE_ENTITY)));

        Obstacle obstacle = obstacleRepository.save(obstacleToSave);
        return ObstacleResponse.fromObstacle(obstacle);
    }
}
