package dev.przbetkier.routemesh.api.response;

import dev.przbetkier.routemesh.domain.node.Node;

import java.util.Set;
import java.util.stream.Collectors;

public class NodeResponse {

    private final Long id;
    private final String name;
    private final Double latitude;
    private final Double longitude;
    private final Set<NodeRoad> startRoads;
    private final Set<NodeRoad> endRoads;

    private NodeResponse(Long id, String name, Double latitude, Double longitude, Set<NodeRoad> startRoads,
                         Set<NodeRoad> endRoads) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.startRoads = startRoads;
        this.endRoads = endRoads;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Set<NodeRoad> getStartRoads() {
        return startRoads;
    }

    public Set<NodeRoad> getEndRoads() {
        return endRoads;
    }

    public static NodeResponse fromNode(Node node) {
        return new NodeResponse(node.getId(),
                                node.getName(),
                                node.getLatitude(),
                                node.getLongitude(),
                                node.getStartRoads()
                                        .stream()
                                        .map(r -> new NodeRoad(r.getId(), r.getName()))
                                        .collect(Collectors.toSet()),
                                node.getEndRoads()
                                        .stream()
                                        .map(r -> new NodeRoad(r.getId(), r.getName()))
                                        .collect(Collectors.toSet()));
    }

    public static class NodeRoad {
        private final Long id;
        private final String name;

        NodeRoad(Long id, String name) {
            this.id = id;
            this.name = name;
        }

        public Long getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }
}
