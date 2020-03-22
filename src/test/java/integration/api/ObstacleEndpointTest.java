package integration.api;

import com.github.dockerjava.api.model.ErrorResponse;
import dev.przbetkier.routemesh.api.response.ObstacleResponse;
import dev.przbetkier.routemesh.domain.obstacle.Obstacle;
import integration.IntegrationTest;
import integration.commons.ObstacleFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static java.util.Objects.requireNonNull;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ObstacleEndpointTest extends IntegrationTest {

    @Test
    @DisplayName("should get all obstacles")
    public void shouldGetAllObstacles() {
        // given
        List<Obstacle> obstacles = List.of(ObstacleFactory.simpleWithName("Obstacle 1"),
                                           ObstacleFactory.simpleWithName("Obstacle 2"),
                                           ObstacleFactory.simpleWithName("Obstacle 3"));

        List<String> obstacleNames = obstacles.stream().map(Obstacle::getName).collect(Collectors.toList());

        obstacleRepository.saveAll(obstacles);

        // when
        ResponseEntity<ObstacleResponse[]> response = restTemplate.getForEntity(localUrl("/obstacles"),
                                                                                ObstacleResponse[].class);

        // then
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(obstacles.size(), requireNonNull(response.getBody()).length);
        stream(response.getBody()).forEach(obstacleResponse -> assertTrue(obstacleNames.contains(obstacleResponse.getName())));
    }

    @Test
    @DisplayName("should get obstacles by id")
    public void shouldGetObstacleById() {
        // given
        var obs = ObstacleFactory.simpleWithName("Obstacle 1");
        var road = obs.getRoad();

        roadRepository.save(road);
        Obstacle obstacle = obstacleRepository.save(obs);
        Long obstacleId = obstacle.getId();

        // when
        ResponseEntity<ObstacleResponse> response = restTemplate.getForEntity(localUrl("/obstacles/" + obstacleId),
                                                                              ObstacleResponse.class);

        // then
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        var obstacleResponse = response.getBody();
        assertAll("properties",
                  () -> assertEquals(obstacle.getId(), obstacleResponse.getId()),
                  () -> assertEquals(obstacle.getName(), obstacleResponse.getName()),
                  () -> assertEquals(obstacle.getCity(), obstacleResponse.getCity()),
                  () -> assertEquals(obstacle.getComment(), obstacleResponse.getComment()),
                  () -> assertEquals(obstacle.getUrl(), obstacleResponse.getUrl()),
                  () -> assertEquals(obstacle.getRoad().getId(), obstacleResponse.getRoadId()),
                  () -> assertEquals(obstacle.getMilestone(), obstacleResponse.getMilestone()),
                  () -> assertEquals(obstacle.getLatitude(), obstacleResponse.getLatitude()),
                  () -> assertEquals(obstacle.getLongitude(), obstacleResponse.getLongitude()),
                  () -> assertEquals(obstacle.getType(), obstacleResponse.getType()));
    }

    @Test
    @DisplayName("should get 404 status code for not existing obstacle")
    public void shouldGetNotFoundStatusForNotExistingObstacle() {
        // when
        ResponseEntity<ErrorResponse> response = restTemplate.getForEntity(localUrl("/obstacles/" + 1234),
                                                                           ErrorResponse.class);

        // then
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    @DisplayName("should delete obstacle")
    public void shouldDeleteObstacle() {
        // given
        var obs = ObstacleFactory.simpleWithName("Obstacle 1");
        var road = obs.getRoad();

        roadRepository.save(road);
        Obstacle obstacle = obstacleRepository.save(obs);
        Long obstacleId = obstacle.getId();

        // then
        assertEquals(1, obstacleRepository.count());

        // when
        var response = restTemplate.exchange(localUrl("/obstacles/" + obstacleId), HttpMethod.DELETE, null, ResponseEntity.class);

        // then
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(0, obstacleRepository.count());
    }
}
