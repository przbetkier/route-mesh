package integration.api;

import com.github.dockerjava.api.model.ErrorResponse;
import dev.przbetkier.routemesh.api.response.HeightObstacleResponse;
import dev.przbetkier.routemesh.api.response.ObstacleResponse;
import dev.przbetkier.routemesh.domain.obstacle.Obstacle;
import dev.przbetkier.routemesh.domain.obstacle.height.HeightObstacle;
import integration.IntegrationTest;
import integration.commons.HeightObstacleRequestFactory;
import integration.commons.RoadFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.stream.Collectors;

import static dev.przbetkier.routemesh.domain.obstacle.ObstacleType.HEIGHT;
import static integration.commons.ObstacleFactory.simpleHeightObstacle;
import static integration.commons.ObstacleFactory.simpleWithName;
import static java.util.Arrays.stream;
import static java.util.Objects.requireNonNull;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HeightObstacleEndpointTest extends IntegrationTest {

    @Test
    @DisplayName("should get all height obstacles")
    public void shouldGetAllHeightObstacles() {
        // given
        List<HeightObstacle> obstacles = List.of(simpleHeightObstacle("Obstacle 1"),
                                                 simpleHeightObstacle("Obstacle 2"),
                                                 simpleHeightObstacle("Obstacle 3"));

        List<String> obstacleNames = obstacles.stream().map(Obstacle::getName).collect(Collectors.toList());

        obstacleRepository.saveAll(obstacles);

        // when
        ResponseEntity<HeightObstacleResponse[]> response = restTemplate.getForEntity(localUrl("/height-obstacles"),
                                                                                      HeightObstacleResponse[].class);

        // then
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(obstacles.size(), requireNonNull(response.getBody()).length);
        stream(response.getBody()).forEach(obs -> assertAll("properties",
                                                            () -> assertTrue(obstacleNames.contains(obs.getName())),
                                                            () -> assertEquals(HEIGHT, obs.getType())));
    }

    @Test
    @DisplayName("should get height obstacle by ID")
    public void shouldGetHeightObstacleById() {
        // given
        var obstacle = simpleHeightObstacle("Obstacle 1");
        var saved = obstacleRepository.save(obstacle);

        // when
        var response = restTemplate.getForEntity(localUrl("/height-obstacles/" + saved.getId()),
                                                 HeightObstacleResponse.class);

        // then
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        var obstacleResponse = response.getBody();
        assertAll("default properties",
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
        assertAll("height obstacle properties",
                  () -> assertEquals(obstacle.getSubtype(), obstacleResponse.getSubtype()),
                  () -> assertEquals(obstacle.getLimit(), obstacleResponse.getLimit()),
                  () -> assertEquals(obstacle.getProfile(), obstacleResponse.getProfile()),
                  () -> assertEquals(obstacle.getRange(), obstacleResponse.getRange()));
    }

    @Test
    @DisplayName("should get 404 status code for not existing height obstacle")
    public void shouldGetNotFoundStatusForNotExistingHeightObstacle() {
        // when
        ResponseEntity<ErrorResponse> response = restTemplate.getForEntity(localUrl("/height-obstacles/" + 1234),
                                                                           ErrorResponse.class);

        // then
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    @DisplayName("should save new height obstacle on valid request")
    public void shouldSaveNewObstacle() {
        // when
        var road = roadRepository.save(RoadFactory.simple());
        var requestBody = HeightObstacleRequestFactory.simpleWithRoadId(road.getId());

        var response = restTemplate.postForEntity(localUrl("/height-obstacles"), requestBody, ObstacleResponse.class);

        // then
        assertEquals(201, response.getStatusCodeValue());
        assertEquals(1, obstacleRepository.count());
    }
}
