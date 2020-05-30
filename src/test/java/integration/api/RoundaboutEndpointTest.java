package integration.api;

import dev.przbetkier.routemesh.api.request.RoundAboutRequest;
import dev.przbetkier.routemesh.api.response.NodeResponse;
import dev.przbetkier.routemesh.domain.node.Node;
import dev.przbetkier.routemesh.domain.node.NodeType;
import dev.przbetkier.routemesh.domain.road.Road;
import integration.IntegrationTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.Set;

import static dev.przbetkier.routemesh.api.request.RoundAboutRequest.ExitRequest;
import static integration.commons.NodeFactory.simpleWithName;
import static integration.commons.RoadFactory.simpleFromNodes;

class RoundaboutEndpointTest extends IntegrationTest {

    @Test
    public void shouldCreateNewRoundabout() {
        // given
        Node roundabout = nodeRepository.save(simpleWithName("roundabout"));

        Node pointA = nodeRepository.save(simpleWithName("point A"));
        Node pointB = nodeRepository.save(simpleWithName("point B"));
        Node pointC = nodeRepository.save(simpleWithName("point C"));

        Road roundAboutToARoad = roadRepository.save(simpleFromNodes("roundabout to A", roundabout, pointA));
        Road roundAboutToBRoad = roadRepository.save(simpleFromNodes("roundabout to B", roundabout, pointB));
        Road roundAboutToCRoad = roadRepository.save(simpleFromNodes("roundabout to C", roundabout, pointC));


        RoundAboutRequest request = new RoundAboutRequest(roundabout.getId(),
                                                          5000,
                                                          15000,
                                                          Set.of(new ExitRequest(roundAboutToARoad.getId(), 40, 90),
                                                                 new ExitRequest(roundAboutToBRoad.getId(), 135, 175),
                                                                 new ExitRequest(roundAboutToCRoad.getId(), 220, 275)));

        HttpEntity<RoundAboutRequest> entity = new HttpEntity<>(request);

        // when
        ResponseEntity<Void> response = restTemplate.exchange(localUrl("/roundabouts"),
                                                              HttpMethod.POST,
                                                              entity,
                                                              Void.class);

        // then
        Assertions.assertTrue(response.getStatusCode().is2xxSuccessful());
        Assertions.assertEquals(1, roundaboutRepository.count());
        Assertions.assertEquals(3, roundaboutRepository.findAll().stream().findFirst().get().getExits().size());
        Assertions.assertEquals(1, nodeRepository.findById(roundabout.getId()).get().getRoundabouts().size());

        // and

        ResponseEntity<NodeResponse> nodeResponse = restTemplate.getForEntity(localUrl("/nodes/" + roundabout.getId()),
                                                                              NodeResponse.class);

        // then
        Assertions.assertEquals(nodeResponse.getBody().getType(), NodeType.ROUNDABOUT.name());
    }
}
