package dev.przbetkier.routemesh.api.request;

public class ObstacleRequest {

    private final Long roadId;
    private final String name;
    private final String city;
    private final Double latitude;
    private final Double longitude;
    private final boolean immovable;
    private final Double milestone;
    private final String url;
    private final String comment;
    private final String type;
    private final String subtype;

    public ObstacleRequest(Long roadId, String name, String city, Double latitude, Double longitude, boolean immovable,
                           Double milestone, String url, String comment, String type, String subtype) {
        this.roadId = roadId;
        this.name = name;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
        this.immovable = immovable;
        this.milestone = milestone;
        this.url = url;
        this.comment = comment;
        this.type = type;
        this.subtype = subtype;
    }

    public Long getRoadId() {
        return roadId;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public boolean isImmovable() {
        return immovable;
    }

    public Double getMilestone() {
        return milestone;
    }

    public String getUrl() {
        return url;
    }

    public String getComment() {
        return comment;
    }

    public String getType() {
        return type;
    }

    public String getSubtype() {
        return subtype;
    }
}
