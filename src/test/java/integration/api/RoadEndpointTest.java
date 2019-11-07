package integration.api;

import dev.przbetkier.routemesh.api.request.RoadRequest;
import dev.przbetkier.routemesh.api.response.RoadResponse;
import dev.przbetkier.routemesh.domain.node.Node;
import dev.przbetkier.routemesh.domain.road.Road;
import integration.IntegrationTest;
import integration.commons.NodeFactory;
import integration.commons.RoadFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import static integration.commons.NodeFactory.simpleWithName;
import static integration.commons.RoadRequestFactory.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.http.HttpMethod.*;
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
        ResponseEntity<RoadResponse[]> response = restTemplate.getForEntity(localUrl("/roads"), RoadResponse[].class);

        // then
        assertEquals(OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(roadsCount, response.getBody().length);
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

    private void createRoads(int numberOfRoads) {
        IntStream.range(0, numberOfRoads).forEach(i -> {

            Node start = nodeRepository.save(simpleWithName("start" + i));
            Node end = nodeRepository.save(simpleWithName("end" + i));

            roadRepository.save(RoadFactory.simpleFromNodes("road" + i, start, end));
        });
    }
}
