package integration.api;

import com.github.dockerjava.api.model.ErrorResponse;
import dev.przbetkier.routemesh.api.response.ObstacleResponse;
import dev.przbetkier.routemesh.domain.obstacle.obstructions.HeightObstruction;
import dev.przbetkier.routemesh.domain.obstacle.obstructions.HeightProfile;
import dev.przbetkier.routemesh.domain.obstacle.obstructions.Obstructions;
import integration.IntegrationTest;
import integration.commons.ObstacleRequestFactory;
import integration.commons.RoadFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static dev.przbetkier.routemesh.domain.obstacle.obstructions.ObstacleHeightSubtype.DEVICE;
import static integration.commons.ObstacleFactory.simpleWithName;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HeightObstacleEndpointTest extends IntegrationTest {


    @Test
    @DisplayName("should get height obstacle by ID")
    public void shouldGetHeightObstacleById() {
        // given
        var obstacle = simpleWithName("Obstacle 1");
        var saved = obstacleRepository.save(obstacle);

        // when
        var response = restTemplate.getForEntity(localUrl("/obstacles/" + saved.getId()), ObstacleResponse.class);

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
                  () -> assertEquals(obstacle.getLongitude(), obstacleResponse.getLongitude()));
        assertAll("height obstacle properties",
                  () -> assertEquals(obstacle.getHeightObstruction().getSubtype(),
                                     obstacleResponse.getObstructions().getHeight().getSubtype()),
                  () -> assertEquals(obstacle.getHeightObstruction().getLimit(),
                                     obstacleResponse.getObstructions().getHeight().getLimit()),
                  () -> assertEquals(obstacle.getHeightObstruction().getProfile(),
                                     obstacleResponse.getObstructions().getHeight().getProfile()),
                  () -> assertEquals(obstacle.getHeightObstruction().getRange(),
                                     obstacleResponse.getObstructions().getHeight().getRange()));
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
        var obstructions = new Obstructions(new HeightObstruction(2000, HeightProfile.LINE, 300, DEVICE));
        var requestBody = ObstacleRequestFactory.simple(road.getId(), obstructions);

        var response = restTemplate.postForEntity(localUrl("/obstacles"), requestBody, ObstacleResponse.class);

        // then
        assertEquals(201, response.getStatusCodeValue());
        assertEquals(1, obstacleRepository.count());
    }
}
