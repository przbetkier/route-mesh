package dev.przbetkier.routemesh.domain.obstacle;

import dev.przbetkier.routemesh.api.request.ObstacleRequest;
import dev.przbetkier.routemesh.api.response.ObstacleResponse;
import dev.przbetkier.routemesh.domain.common.EntityNotFoundException;
import dev.przbetkier.routemesh.domain.road.Road;
import dev.przbetkier.routemesh.domain.road.RoadsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        Road road = roadsService.getById(request.getRoadId())
                .orElseThrow(() -> new EntityNotFoundException("road", request.getRoadId()));

        var obstacle = new ObstacleBuilder().withName(request.getName())
                .withCity(request.getCity())
                .withLatitude(request.getLatitude())
                .withLongitude(request.getLongitude())
                .immovable(request.isImmovable())
                .withMilestone(request.getMilestone())
                .withUrl(request.getUrl())
                .obstructingRoad(road)
                .withHeightObstruction(request.getObstructions().getHeight())
                .withWeightObstruction(request.getObstructions().getWeight())
                .withWidthObstruction(request.getObstructions().getWidth())
                .build();

        return ObstacleResponse.fromObstacle(obstacleRepository.save(obstacle));
    }
}
