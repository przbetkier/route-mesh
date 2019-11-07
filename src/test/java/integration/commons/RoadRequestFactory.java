package integration.commons;

import dev.przbetkier.routemesh.api.request.RoadRequest;
import dev.przbetkier.routemesh.domain.road.RoadDirection;
import dev.przbetkier.routemesh.domain.road.RoadType;

import java.util.Arrays;
import java.util.Collections;

public class RoadRequestFactory {

    public static RoadRequest simpleRoadRequest(String name, Long startNode, Long endNode) {
        return new RoadRequest(name,
                               startNode,
                               endNode,
                               RoadDirection.TWO_WAY,
                               RoadType.GP,
                               2,
                               Arrays.asList("10", "101", "202"),
                               Arrays.asList(111.11, 222.222),
                               777d,
                               7000,
                               Collections.emptyList());
    }
}
