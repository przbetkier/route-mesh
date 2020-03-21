package dev.przbetkier.routemesh.api.response;

import dev.przbetkier.routemesh.domain.obstacle.Obstacle;
import dev.przbetkier.routemesh.domain.obstacle.ObstacleType;

public class ObstacleResponse {

    private final Long id;
    private final String name;
    private final String city;
    private final Double latitude;
    private final Double longitude;
    private final boolean immovable;
    private final Double milestone;
    private final String url;
    private final String comment;
    private final ObstacleType obstacleType;
    private final Long roadId;

    public ObstacleResponse(Long id, String name, String city, Double latitude, Double longitude, boolean immovable,
                            Double milestone, String url, String comment, ObstacleType obstacleType, Long roadId) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
        this.immovable = immovable;
        this.milestone = milestone;
        this.url = url;
        this.comment = comment;
        this.obstacleType = obstacleType;
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

    public String getCity() {
        return city;
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

    public ObstacleType getObstacleType() {
        return obstacleType;
    }

    public static ObstacleResponse fromObstacle(Obstacle obstacle) {
        return new ObstacleResponse(obstacle.getId(),
                                    obstacle.getName(),
                                    obstacle.getCity(),
                                    obstacle.getLatitude(),
                                    obstacle.getLongitude(),
                                    obstacle.isImmovable(),
                                    obstacle.getMilestone(),
                                    obstacle.getUrl(),
                                    obstacle.getComment(),
                                    obstacle.getObstacleType(),
                                    obstacle.getRoad().getId());
    }
}
