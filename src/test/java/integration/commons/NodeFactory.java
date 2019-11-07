package integration.commons;

import dev.przbetkier.routemesh.domain.node.Node;

import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

public class NodeFactory {

    private static final Double MIN_LATITUDE = 49.00238;
    private static final Double MAX_LATITUDE = 54.833333;
    private static final Double MIN_LONGITUDE = 14.12298;
    private static final Double MAX_LONGITUDE = 24.14585;

    public static Node simpleWithName(String name) {
        return new Node(name,
                        randomPolishLatitude(),
                        randomPolishLongitude(),
                        Collections.emptySet(),
                        Collections.emptySet());
    }

    private static Double randomPolishLatitude() {
        return ThreadLocalRandom.current().nextDouble(MIN_LATITUDE, MAX_LATITUDE);
    }

    private static Double randomPolishLongitude() {
        return ThreadLocalRandom.current().nextDouble(MIN_LONGITUDE, MAX_LONGITUDE);
    }
}
