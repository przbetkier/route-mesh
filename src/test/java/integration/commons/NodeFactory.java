package integration.commons;

import dev.przbetkier.routemesh.domain.node.Node;

import java.util.Collections;

import static integration.commons.helpers.LatitudeHelper.randomPolishLatitude;
import static integration.commons.helpers.LatitudeHelper.randomPolishLongitude;

public class NodeFactory {

    public static Node simpleWithName(String name) {
        return new Node(name,
                        randomPolishLatitude(),
                        randomPolishLongitude(),
                        Collections.emptySet(),
                        Collections.emptySet());
    }
}
