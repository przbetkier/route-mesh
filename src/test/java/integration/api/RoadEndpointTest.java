package integration.api;

import dev.przbetkier.routemesh.api.request.RoadRequest;
import dev.przbetkier.routemesh.api.response.RoadResponse;
import dev.przbetkier.routemesh.domain.node.Node;
import dev.przbetkier.routemesh.domain.obstacle.Obstacle;
import dev.przbetkier.routemesh.domain.road.Road;
import integration.IntegrationTest;
import integration.commons.NodeFactory;
import integration.commons.ObstacleFactory;
import integration.commons.RoadFactory;
import integration.commons.helpers.RestResponsePage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import static integration.commons.NodeFactory.simpleWithName;
import static integration.commons.RoadRequestFactory.simpleRoadRequest;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

class RoadEndpointTest extends IntegrationTest {

    @Test
    @DisplayName("should get all roads")
    void shouldGetAllRoads() {
        // given
        int roadsCount = ThreadLocalRandom.current().nextInt(1, 10);
        createRoads(roadsCount);

        // when
        var response = restTemplate.exchange(localUrl("/roads?page=0"), GET, null,
                                             // DON'T REMOVE TYPE (https://bugs.openjdk.java.net/browse/JDK-8203195)
                                             new ParameterizedTypeReference<RestResponsePage<RoadResponse>>() {});

        // then
        assertEquals(OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(roadsCount, response.getBody().getContent().size());
    }

    @Test
    @DisplayName("should get single road with obstacle")
    void shouldGetSingleRoad() {
        // given

        var obstacle = ObstacleFactory.simpleWithName("Obstacle");
        var road = obstacle.getRoad();
        var savedRoad = roadRepository.save(road);
        obstacleRepository.save(obstacle);

        // when
        var response = restTemplate.getForEntity(localUrl("/roads/" + savedRoad.getId()), RoadResponse.class);

        // then
        assertEquals(OK, response.getStatusCode());
        assertNotNull(response.getBody());
        var body = response.getBody();
        assertAll("properties",
                  () -> assertEquals(savedRoad.getName(), body.getName()),
                  () -> assertEquals(savedRoad.getDirection(), body.getRoadDirection()),
                  () -> assertEquals(savedRoad.getStartNode().getId(), body.getStartNode().getId()),
                  () -> assertEquals(savedRoad.getEndNode().getName(), body.getEndNode().getName()),
                  () -> assertEquals(savedRoad.getMaxAxleLoad(), body.getMaxAxleLoad()),
                  () -> assertEquals(savedRoad.getLines(), body.getLines()),
                  () -> assertEquals(savedRoad.getWidth(), body.getWidth()),
                  () -> assertEquals(1, response.getBody().getObstacles().size()));
    }

    @Test
    @DisplayName("should create new road")
    void shouldCreateNewRoad() {
        // given
        Node start = nodeRepository.save(NodeFactory.simpleWithName("start"));
        Node end = nodeRepository.save(NodeFactory.simpleWithName("end"));
        RoadRequest roadRequest = simpleRoadRequest("road-1", start.getId(), end.getId());
        HttpEntity<RoadRequest> entity = new HttpEntity<>(roadRequest);

        // when
        ResponseEntity<RoadResponse> response = restTemplate.exchange(localUrl("/roads"),
                                                                      POST,
                                                                      entity,
                                                                      RoadResponse.class);

        // then
        assertEquals(CREATED, response.getStatusCode());
        assertNotNull(response.getBody());

        assertEquals(1, roadRepository.count());
        Optional<Road> savedRoad = roadRepository.findById(response.getBody().getId());
        assertTrue(savedRoad.isPresent());
        savedRoad.ifPresent(r -> assertAll("properties",
                                           () -> assertEquals(roadRequest.getName(), r.getName()),
                                           () -> assertEquals(roadRequest.getDirection(), r.getDirection()),
                                           () -> assertEquals(roadRequest.getStartNode(), r.getStartNode().getId()),
                                           () -> assertEquals(roadRequest.getEndNode(), r.getEndNode().getId()),
                                           () -> assertEquals(roadRequest.getMaxAxleLoad(), r.getMaxAxleLoad()),
                                           () -> assertEquals(roadRequest.getLines(), r.getLines()),
                                           () -> assertEquals(roadRequest.getWidth(), r.getWidth()),
                                           () -> assertArrayEquals(roadRequest.getKmRange().toArray(),
                                                                   r.getKmRange().toArray())));
    }

    @Test
    @DisplayName("should delete road")
    void shouldDeleteRoad() {
        // given
        Node start = nodeRepository.save(NodeFactory.simpleWithName("start"));
        Node end = nodeRepository.save(NodeFactory.simpleWithName("end"));
        Road road = roadRepository.save(RoadFactory.simpleFromNodes("road", start, end));

        // expect
        assertEquals(1, roadRepository.count());

        // when
        ResponseEntity<Void> response = restTemplate.exchange(localUrl("/roads/" + road.getId()),
                                                              DELETE,
                                                              null,
                                                              Void.class);

        // expect
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertEquals(0, roadRepository.count());
    }

    @Test
    @DisplayName("should delete road and connected obstacles and obstructions")
    void shouldDeleteRoadAndConnectedObstacles() {
        // given
        Road road = roadRepository.save(RoadFactory.simple());
        Set<Obstacle> obstacles = Set.of(
                ObstacleFactory.simpleForRoad("obstacle2", road)
        );
        obstacleRepository.saveAll(obstacles);

        // expect
        assertEquals(1, roadRepository.count());
        assertEquals(1, obstacleRepository.count());
        assertTrue(obstructionRepository.count() > 0);

        // when
        ResponseEntity<Void> response = restTemplate.exchange(localUrl("/roads/" + road.getId()),
                                                              DELETE,
                                                              null,
                                                              Void.class);

        // expect
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertEquals(0, roadRepository.count());
        assertEquals(0, obstacleRepository.count());
        assertEquals(0, obstructionRepository.count());
    }

    private void createRoads(int numberOfRoads) {
        IntStream.range(0, numberOfRoads).forEach(i -> {

            Node start = nodeRepository.save(simpleWithName("start" + i));
            Node end = nodeRepository.save(simpleWithName("end" + i));

            roadRepository.save(RoadFactory.simpleFromNodes("road" + i, start, end));
        });
    }
}
