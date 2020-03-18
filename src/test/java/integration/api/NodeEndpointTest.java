package integration.api;

import dev.przbetkier.routemesh.api.response.NodeResponse;
import dev.przbetkier.routemesh.domain.node.Node;
import integration.IntegrationTest;
import integration.commons.NodeFactory;
import integration.commons.RoadFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.http.HttpStatus.OK;

class NodeEndpointTest extends IntegrationTest {

    @Test
    @DisplayName("should get one node")
    void shouldGetOneNode() {
        // given
        Node node = nodeRepository.save(NodeFactory.simpleWithName("node-1"));

        // when
        ResponseEntity<NodeResponse> response = restTemplate.getForEntity(localUrl("/nodes/" + node.getId()),
                                                                          NodeResponse.class);

        // then
        assertEquals(OK, response.getStatusCode());
        NodeResponse nodeResponse = response.getBody();
        assertNotNull(nodeResponse);
        assertAll("properties",
                  () -> assertEquals(node.getId(), nodeResponse.getId()),
                  () -> assertEquals(node.getName(), nodeResponse.getName()),
                  () -> assertEquals(node.getLatitude(), nodeResponse.getLatitude()),
                  () -> assertEquals(node.getLongitude(), nodeResponse.getLongitude()));
    }

    @Test
    @DisplayName("should get all nodes")
    void shouldGetAllNodes() {
        // given
        Node start = NodeFactory.simpleWithName("start");
        Node end = NodeFactory.simpleWithName("end");
        nodeRepository.saveAll(Arrays.asList(start, end));
        roadRepository.save(RoadFactory.simpleFromNodes("road-1", start, end));

        // when
        ResponseEntity<NodeResponse[]> response = restTemplate.getForEntity(localUrl("/nodes"), NodeResponse[].class);

        // then
        assertEquals(OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().length);
    }
}
