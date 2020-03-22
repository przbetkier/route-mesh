package dev.przbetkier.routemesh.api.response;

import dev.przbetkier.routemesh.domain.obstacle.ObstacleSubtype;
import dev.przbetkier.routemesh.domain.obstacle.ObstacleType;
import dev.przbetkier.routemesh.domain.obstacle.height.HeightObstacle;
import dev.przbetkier.routemesh.domain.obstacle.height.HeightProfile;

public class HeightObstacleResponse extends ObstacleResponse {

    private Integer limit;
    private HeightProfile profile;
    private Integer range;

    public HeightObstacleResponse(Long id, String name, String city, Double latitude, Double longitude,
                                  boolean immovable, Double milestone, String url, String comment,
                                  ObstacleType obstacleType, ObstacleSubtype subtype, Long roadId) {
        super(id, name, city, latitude, longitude, immovable, milestone, url, comment, obstacleType, subtype, roadId);
    }

    public HeightObstacleResponse(HeightObstacle obstacle) {
        super(obstacle.getId(),
              obstacle.getName(),
              obstacle.getCity(),
              obstacle.getLatitude(),
              obstacle.getLongitude(),
              obstacle.isImmovable(),
              obstacle.getMilestone(),
              obstacle.getUrl(),
              obstacle.getComment(),
              obstacle.getType(),
              obstacle.getSubtype(),
              obstacle.getRoad().getId());
        this.limit = obstacle.getLimit();
        this.profile = obstacle.getProfile();
        this.range = obstacle.getRange();
    }

    public Integer getLimit() {
        return limit;
    }

    public HeightProfile getProfile() {
        return profile;
    }

    public Integer getRange() {
        return range;
    }

}
