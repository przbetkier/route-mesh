package dev.przbetkier.routemesh.api.request;

import dev.przbetkier.routemesh.domain.obstacle.height.HeightObstacleType;
import dev.przbetkier.routemesh.domain.obstacle.height.HeightProfile;

public class HeightObstacleRequest extends ObstacleRequest {

    private final Integer limit;
    private final HeightProfile profile;
    private final Integer range;
    private final HeightObstacleType heightObstacleType;

    public HeightObstacleRequest(Long roadId, String name, String city, Double latitude, Double longitude,
                                 boolean immovable, Double milestone, String url, String comment,
                                 String obstacleType, Integer limit, HeightProfile profile, Integer range,
                                 HeightObstacleType heightObstacleType) {
        super(roadId, name, city, latitude, longitude, immovable, milestone, url, comment, obstacleType);
        this.limit = limit;
        this.profile = profile;
        this.range = range;
        this.heightObstacleType = heightObstacleType;
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

    public HeightObstacleType getHeightObstacleType() {
        return heightObstacleType;
    }
}
