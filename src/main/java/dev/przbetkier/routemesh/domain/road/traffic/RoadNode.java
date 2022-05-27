package dev.przbetkier.routemesh.domain.road.traffic;

public class RoadNode {

    private final Double latitude;
    private final Double longitude;

    public RoadNode(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }
}
