package dev.przbetkier.routemesh.api;

import dev.przbetkier.routemesh.api.response.ObstacleResponse;
import dev.przbetkier.routemesh.domain.obstacle.Obstacle;
import dev.przbetkier.routemesh.domain.obstacle.ObstacleRepository;
import dev.przbetkier.routemesh.domain.road.RoadRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/obstacles")
class ObstacleEndpoint {

    private final ObstacleRepository obstacleRepository;
    private final RoadRepository roadRepository;

    ObstacleEndpoint(ObstacleRepository obstacleRepository, RoadRepository roadRepository) {
        this.obstacleRepository = obstacleRepository;
        this.roadRepository = roadRepository;
    }

    @GetMapping
    List<ObstacleResponse> getAll() {
        return obstacleRepository.findAll()
                .stream()
                .map(ob -> new ObstacleResponse(ob.getId(),
                                                ob.getName(),
                                                ob.getLatitude(),
                                                ob.getLongitude(),
                                                ob.getRoad().getId()))
                .collect(Collectors.toList());
    }

    @GetMapping("/elo")
    public ObstacleResponse saveObstacle() {
        Obstacle obstacle = new Obstacle("Taka tam przeszkoda",
                                         42.44,
                                         11.11,
                                         roadRepository.findAll(PageRequest.of(1, 1)).get().findFirst().get());
        Obstacle saved = this.obstacleRepository.save(obstacle);
        return new ObstacleResponse(saved.getId(),
                                    saved.getName(),
                                    saved.getLatitude(),
                                    saved.getLongitude(),
                                    saved.getRoad().getId());
    }

}
