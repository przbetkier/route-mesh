package integration.commons;

import dev.przbetkier.routemesh.domain.node.Node;
import dev.przbetkier.routemesh.domain.obstacle.Obstacle;
import dev.przbetkier.routemesh.domain.road.Road;
import dev.przbetkier.routemesh.domain.road.RoadBuilder;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

import static dev.przbetkier.routemesh.domain.road.RoadDirection.TWO_WAY;
import static dev.przbetkier.routemesh.domain.road.RoadType.G;
import static java.util.Arrays.asList;
import static java.util.Collections.emptySet;

public class RoadFactory {

    public static Road simple() {
        return simpleFromNodes("Road 1", NodeFactory.simpleWithName("Węzeł A"), NodeFactory.simpleWithName("Węzeł B"));
    }

    public static Road simpleFromNodes(String name, Node start, Node end) {
        return RoadBuilder.builder()
                .withName(name)
                .withStartNode(start)
                .withEndNode(end)
                .withDirection(TWO_WAY)
                .withAdmins(emptySet())
                .withType(G)
                .withNumbers(emptySet())
                .withKmRange(new TreeSet<>(asList(11.111, 222.22)))
                .withLines(4)
                .withMaxAxleLoad(123.0)
                .withWidth(7000)
                .build();
    }

    public static Road simpleWithObstacles(String name, Node start, Node end, Set<Obstacle> obstacles) {
        return RoadBuilder.builder()
                .withName(name)
                .withStartNode(start)
                .withEndNode(end)
                .withDirection(TWO_WAY)
                .withAdmins(emptySet())
                .withType(G)
                .withNumbers(emptySet())
                .withKmRange(new TreeSet<>(asList(11.111, 222.22)))
                .withLines(4)
                .withMaxAxleLoad(123.0)
                .withWidth(7000)
                .withObstacles(obstacles)
                .build();
    }

}
