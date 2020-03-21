package dev.przbetkier.routemesh.domain.obstacle;

import java.util.List;

public enum ObstacleType {
    HEIGHT, WIDTH, WEIGHT;

    public static ObstacleType fromString(String value) {
        return List.of(ObstacleType.values())
                .stream()
                .filter(v -> v.name().equals(value))
                .findFirst()
                .orElseThrow(() -> new UnsupportedObstacleTypeException(String.format(
                        "%s obstacle type is not supported",
                        value)));
    }
}
