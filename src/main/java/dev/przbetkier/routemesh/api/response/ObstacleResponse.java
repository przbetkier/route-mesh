package dev.przbetkier.routemesh.api.response;

public class ObstacleResponse {

    private final Long id;
    private final String name;
    private final Double latitude;
    private final Double longitude;
    private final Long roadId;

    public ObstacleResponse(Long id, String name, Double latitude, Double longitude, Long roadId) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.roadId = roadId;
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

    public Long getRoadId() {
        return roadId;
    }
}
