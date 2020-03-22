package dev.przbetkier.routemesh.domain.obstacle;

import java.util.List;

public enum ObstacleSubtype {
    // HEIGHT
    OVERPASS, TUNNEL, DEVICE, CABLES, PIPE, // OTHER
    OTHER;

    public static ObstacleSubtype fromString(String value) {
        return List.of(ObstacleSubtype.values())
                .stream()
                .filter(v -> v.name().equals(value))
                .findFirst()
                .orElseThrow(() -> new UnsupportedObstacleTypeException(String.format(
                        "%s obstacle subtype is not supported",
                        value)));
    }
}
