package dev.przbetkier.routemesh.domain.obstacle;

import dev.przbetkier.routemesh.api.request.HeightObstacleRequest;
import dev.przbetkier.routemesh.api.response.HeightObstacleResponse;
import dev.przbetkier.routemesh.api.response.ObstacleResponse;
import dev.przbetkier.routemesh.domain.common.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static dev.przbetkier.routemesh.domain.obstacle.ObstacleType.HEIGHT;

@Service
public class ObstacleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ObstacleService.class);

    private final ObstacleRepository obstacleRepository;
    private final HeightObstacleService heightObstacleService;

    public ObstacleService(ObstacleRepository obstacleRepository, HeightObstacleService heightObstacleService) {
        this.obstacleRepository = obstacleRepository;
        this.heightObstacleService = heightObstacleService;
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

    public List<ObstacleResponse> getAllByType(ObstacleType type) {
        if (type == HEIGHT) {
            return this.heightObstacleService.getAll()
                    .stream()
                    .map(HeightObstacleResponse::new)
                    .collect(Collectors.toList());
        } else {
            throw unsupportedObstacleTypeException("Unsupported obstacle type");
        }
    }

    public ObstacleResponse getByIdAndType(Long id, ObstacleType obstacleType) {
        if (obstacleType == HEIGHT) {
            return heightObstacleService.getById(id)
                    .map(HeightObstacleResponse::new)
                    .orElseThrow(() -> new EntityNotFoundException("obstacle", id));
        } else {
            throw unsupportedObstacleTypeException(obstacleType.toString());
        }
    }

    public ObstacleResponse saveObstacle(HeightObstacleRequest request) {
        return this.heightObstacleService.save(request);
    }

    public UnsupportedObstacleTypeException unsupportedObstacleTypeException(String type) {
        throw new UnsupportedObstacleTypeException(String.format("Unsupported obstacle type: %s", type));
    }
}
