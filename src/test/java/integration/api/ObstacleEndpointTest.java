package integration.api;

import com.github.dockerjava.api.model.ErrorResponse;
import dev.przbetkier.routemesh.api.response.ObstacleResponse;
import dev.przbetkier.routemesh.domain.obstacle.Obstacle;
import dev.przbetkier.routemesh.domain.obstacle.obstructions.CurvatureObstruction;
import dev.przbetkier.routemesh.domain.obstacle.obstructions.ElevationObstruction;
import dev.przbetkier.routemesh.domain.obstacle.obstructions.HeightObstruction;
import dev.przbetkier.routemesh.domain.obstacle.obstructions.HeightProfile;
import dev.przbetkier.routemesh.domain.obstacle.obstructions.Obstructions;
import dev.przbetkier.routemesh.domain.obstacle.obstructions.WeightObstruction;
import dev.przbetkier.routemesh.domain.obstacle.obstructions.WidthObstruction;
import integration.IntegrationTest;
import integration.commons.ObstacleFactory;
import integration.commons.ObstacleRequestFactory;
import integration.commons.RoadFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.stream.Collectors;

import static dev.przbetkier.routemesh.domain.obstacle.obstructions.ObstacleHeightSubtype.DEVICE;
import static dev.przbetkier.routemesh.domain.obstacle.obstructions.ObstacleWeightSubtype.BRIDGE;
import static dev.przbetkier.routemesh.domain.obstacle.obstructions.ObstacleWidthSubtype.LAMP;
import static java.util.Arrays.stream;
import static java.util.Objects.requireNonNull;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ObstacleEndpointTest extends IntegrationTest {

    @Test
    @DisplayName("should get all obstacles")
    void shouldGetAllObstacles() {
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
    void shouldGetObstacleById() {
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
                  () -> assertNotNull(obstacleResponse.getObstructions()));

        var heightObstruction = obstacle.getHeightObstruction();
        var heightObstructionResponse = obstacleResponse.getObstructions().getHeight();
        assertAll("obstructions",
                  () -> assertEquals(heightObstruction.getLimit(), heightObstructionResponse.getLimit()),
                  () -> assertEquals(heightObstruction.getSubtype(), heightObstructionResponse.getSubtype()),
                  () -> assertEquals(heightObstruction.getProfile(), heightObstructionResponse.getProfile()),
                  () -> assertEquals(heightObstruction.getRange(), heightObstructionResponse.getRange()));
    }

    @Test
    @DisplayName("should get 404 status code for not existing obstacle")
    void shouldGetNotFoundStatusForNotExistingObstacle() {
        // when
        ResponseEntity<ErrorResponse> response = restTemplate.getForEntity(localUrl("/obstacles/" + 1234),
                                                                           ErrorResponse.class);

        // then
        assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    @DisplayName("should delete obstacle and connected obstructions")
    void shouldDeleteObstacleAndConnectedObstructions() {
        // given
        var obs = ObstacleFactory.simpleWithName("Obstacle 1");
        var road = obs.getRoad();

        roadRepository.save(road);
        Obstacle obstacle = obstacleRepository.save(obs);
        Long obstacleId = obstacle.getId();

        // then
        assertEquals(1, obstacleRepository.count());

        // when
        var response = restTemplate.exchange(localUrl("/obstacles/" + obstacleId),
                                             HttpMethod.DELETE,
                                             null,
                                             ResponseEntity.class);

        // then
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(0, obstacleRepository.count());
        assertEquals(0, obstructionRepository.count());
    }

    @Test
    @DisplayName("should save new obstacle with height obstruction on valid request")
    void shouldSaveNewObstacleWithHeightObstruction() {
        // when
        var road = roadRepository.save(RoadFactory.simple());
        var obstructions = new Obstructions(new HeightObstruction(2000, HeightProfile.LINE, 300, DEVICE),
                                            null,
                                            null,
                                            null,
                                            null);
        var requestBody = ObstacleRequestFactory.simple(road.getId(), obstructions);

        var response = restTemplate.postForEntity(localUrl("/obstacles"), requestBody, ObstacleResponse.class);

        // then
        assertEquals(201, response.getStatusCodeValue());
        assertEquals(1, obstacleRepository.count());
    }

    @Test
    @DisplayName("should save new obstacle with weight obstruction on valid request")
    void shouldSaveNewObstacleWithWeightObstruction() {
        // when
        var road = roadRepository.save(RoadFactory.simple());
        var obstructions = new Obstructions(null, new WeightObstruction(2000, 100, BRIDGE), null, null, null);
        var requestBody = ObstacleRequestFactory.simple(road.getId(), obstructions);

        var response = restTemplate.postForEntity(localUrl("/obstacles"), requestBody, ObstacleResponse.class);

        // then
        assertEquals(201, response.getStatusCodeValue());
        assertEquals(1, obstacleRepository.count());
    }

    @Test
    @DisplayName("should save new obstacle with width obstruction on valid request")
    void shouldSaveNewObstacleWithWidthObstruction() {
        // when
        var road = roadRepository.save(RoadFactory.simple());
        var obstructions = new Obstructions(null,
                                            null,
                                            new WidthObstruction(List.of(0, 2000), List.of(600, 200), LAMP, false),
                                            null,
                                            null);
        var requestBody = ObstacleRequestFactory.simple(road.getId(), obstructions);

        var response = restTemplate.postForEntity(localUrl("/obstacles"), requestBody, ObstacleResponse.class);

        // then
        assertEquals(201, response.getStatusCodeValue());
        assertEquals(1, obstacleRepository.count());
    }

    @Test
    @DisplayName("should save new obstacle with elevation obstruction on valid request")
    void shouldSaveNewObstacleWithElevationObstruction() {
        // when
        var road = roadRepository.save(RoadFactory.simple());
        var obstructions = new Obstructions(null, null, null, new ElevationObstruction(10000), null);
        var requestBody = ObstacleRequestFactory.simple(road.getId(), obstructions);

        var response = restTemplate.postForEntity(localUrl("/obstacles"), requestBody, ObstacleResponse.class);

        // then
        assertEquals(201, response.getStatusCodeValue());
        assertEquals(1, obstacleRepository.count());
    }

    @Test
    @DisplayName("should save new obstacle with curvature obstruction on valid request")
    void shouldSaveNewObstacleWithCurvatureObstruction() {
        // when
        var road = roadRepository.save(RoadFactory.simple());
        var obstructions = new Obstructions(null, null, null, null, new CurvatureObstruction(10000, 20000, 5000));
        var requestBody = ObstacleRequestFactory.simple(road.getId(), obstructions);

        var response = restTemplate.postForEntity(localUrl("/obstacles"), requestBody, ObstacleResponse.class);

        // then
        assertEquals(201, response.getStatusCodeValue());
        assertEquals(1, obstacleRepository.count());
    }
}
