package dev.przbetkier.routemesh.api;

import dev.przbetkier.routemesh.api.request.HeightObstacleRequest;
import dev.przbetkier.routemesh.api.response.ObstacleResponse;
import dev.przbetkier.routemesh.domain.obstacle.ObstacleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static dev.przbetkier.routemesh.domain.obstacle.ObstacleType.HEIGHT;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/height-obstacles")
class HeightObstacleEndpoint {

    private final ObstacleService obstacleService;

    HeightObstacleEndpoint(ObstacleService obstacleService) {
        this.obstacleService = obstacleService;
    }

    @GetMapping
    public List<ObstacleResponse> getAllHeightObstacles() {
        return obstacleService.getAllByType(HEIGHT);
    }

    @GetMapping("/{obstacleId}")
    public ObstacleResponse getHeightObstacleById(@PathVariable Long obstacleId) {
        return obstacleService.getByIdAndType(obstacleId, HEIGHT);
    }

    @ResponseStatus(CREATED)
    @PostMapping
    public ObstacleResponse addHeightObstacle(@RequestBody HeightObstacleRequest request) {
        return obstacleService.saveObstacle(request);
    }
}
