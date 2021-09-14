package dev.przbetkier.routemesh.api.response;

import dev.przbetkier.routemesh.domain.node.Node;
import dev.przbetkier.routemesh.domain.restpoint.RestPoint;
import dev.przbetkier.routemesh.domain.roundabout.Roundabout;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class NodeResponse {

    private final Long id;
    private final String name;
    private final Double latitude;
    private final Double longitude;
    private final Set<NodeRoad> startRoads;
    private final Set<NodeRoad> endRoads;
    private final String type;
    private final Long roundaboutId;
    private final Long restpointId;

    private NodeResponse(Long id, String name, Double latitude, Double longitude, Set<NodeRoad> startRoads,
                         Set<NodeRoad> endRoads, String type, Long roundaboutId, Long restpointId) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.startRoads = startRoads;
        this.endRoads = endRoads;
        this.type = type;
        this.roundaboutId = roundaboutId;
        this.restpointId = restpointId;
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

    public String getType() {
        return type;
    }

    public Long getRoundaboutId() {
        return roundaboutId;
    }

    public Long getRestpointId() {
        return restpointId;
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
                                        .collect(Collectors.toSet()), node.getType().name(),
                                Optional.ofNullable(node.getRoundabout()).map(Roundabout::getId).orElse(null),
                                Optional.ofNullable(node.getRestpoint()).map(RestPoint::getId).orElse(null));
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
