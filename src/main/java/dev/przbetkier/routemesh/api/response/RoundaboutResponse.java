package dev.przbetkier.routemesh.api.response;

import dev.przbetkier.routemesh.domain.roundabout.Roundabout;

import java.util.Set;
import java.util.stream.Collectors;

public class RoundaboutResponse {
    private final Long id;
    private final RoundaboutNode node;
    private final Integer innerDiameter;
    private final Integer outerDiameter;
    private final Set<RoundaboutExit> roundaboutExits;

    public RoundaboutResponse(Long id, RoundaboutNode node, Integer innerDiameter, Integer outerDiameter,
                              Set<RoundaboutExit> roundaboutExits) {
        this.id = id;
        this.node = node;
        this.innerDiameter = innerDiameter;
        this.outerDiameter = outerDiameter;
        this.roundaboutExits = roundaboutExits;
    }

    public static RoundaboutResponse fromRoundabout(Roundabout roundabout) {
        return new RoundaboutResponse(roundabout.getId(),
                                      new RoundaboutNode(roundabout.getNode().getName(),
                                                         roundabout.getNode().getLatitude(),
                                                         roundabout.getNode().getLongitude()),
                                      roundabout.getInnerDiameter(),
                                      roundabout.getOuterDiameter(),
                                      roundabout.getExits()
                                              .stream()
                                              .map(e -> new RoundaboutExit(e.getRoad().getId(),
                                                                           e.getRoad().getName(),
                                                                           e.getStartAngle(),
                                                                           e.getEndAngle()))
                                              .collect(Collectors.toSet()));
    }

    public Long getId() {
        return id;
    }

    public Integer getInnerDiameter() {
        return innerDiameter;
    }

    public Integer getOuterDiameter() {
        return outerDiameter;
    }

    public Set<RoundaboutExit> getRoundaboutExits() {
        return roundaboutExits;
    }

    public RoundaboutNode getNode() {
        return node;
    }

    private static class RoundaboutExit {
        private final Long roadId;
        private final String roadName;
        private final Integer startAngle;
        private final Integer endAngle;

        public RoundaboutExit(Long roadId, String roadName, Integer startAngle, Integer endAngle) {
            this.roadId = roadId;
            this.roadName = roadName;
            this.startAngle = startAngle;
            this.endAngle = endAngle;
        }

        public Long getRoadId() {
            return roadId;
        }

        public String getRoadName() {
            return roadName;
        }

        public Integer getStartAngle() {
            return startAngle;
        }

        public Integer getEndAngle() {
            return endAngle;
        }
    }

    private static class RoundaboutNode {

        private final String name;
        private final Double latitude;
        private final Double longitude;

        public RoundaboutNode(String name, Double latitude, Double longitude) {
            this.name = name;
            this.latitude = latitude;
            this.longitude = longitude;
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
    }
}
