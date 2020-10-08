package dev.przbetkier.routemesh.api.request;

public class RestPointRequest {

    private final Long roadId;
    private final String name;
    private final Double latitude;
    private final Double longitude;
    private final Double milestone;
    private final Double width;
    private final Double length;

    public RestPointRequest(Long roadId, String name, Double latitude, Double longitude, Double milestone, Double width,
                            Double length) {
        this.roadId = roadId;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.milestone = milestone;
        this.width = width;
        this.length = length;
    }

    public Long getRoadId() {
        return roadId;
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

    public Double getMilestone() {
        return milestone;
    }

    public Double getWidth() {
        return width;
    }

    public Double getLength() {
        return length;
    }
}
