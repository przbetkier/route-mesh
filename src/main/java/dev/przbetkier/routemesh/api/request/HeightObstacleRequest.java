package dev.przbetkier.routemesh.api.request;

import dev.przbetkier.routemesh.domain.obstacle.height.HeightProfile;

public class HeightObstacleRequest extends ObstacleRequest {

    private final Integer limit;
    private final HeightProfile profile;
    private final Integer range;

    public HeightObstacleRequest(Long roadId, String name, String city, Double latitude, Double longitude,
                                 boolean immovable, Double milestone, String url, String comment, String type,
                                 String subtype, Integer limit, HeightProfile profile, Integer range) {
        super(roadId, name, city, latitude, longitude, immovable, milestone, url, comment, type, subtype);
        this.limit = limit;
        this.profile = profile;
        this.range = range;
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
