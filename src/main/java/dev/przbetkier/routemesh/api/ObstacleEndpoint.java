package dev.przbetkier.routemesh.api;

import dev.przbetkier.routemesh.api.response.ObstacleResponse;
import dev.przbetkier.routemesh.domain.obstacle.ObstacleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/obstacles")
class ObstacleEndpoint {

    private final ObstacleService obstacleService;

    ObstacleEndpoint(ObstacleService obstacleService) {
        this.obstacleService = obstacleService;
    }

    @GetMapping
    public List<ObstacleResponse> getAll() {
        return obstacleService.getAll();
    }

    @GetMapping("/{obstacleId}")
    public ObstacleResponse getById(@PathVariable Long obstacleId) {
        return obstacleService.getById(obstacleId);
    }
}
