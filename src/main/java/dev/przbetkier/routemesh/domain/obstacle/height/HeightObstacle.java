package dev.przbetkier.routemesh.domain.obstacle.height;

import dev.przbetkier.routemesh.domain.obstacle.Obstacle;
import dev.przbetkier.routemesh.domain.obstacle.ObstacleType;
import dev.przbetkier.routemesh.domain.road.Road;
import org.neo4j.ogm.annotation.NodeEntity;
import org.springframework.data.annotation.PersistenceConstructor;

@NodeEntity
public class HeightObstacle extends Obstacle {

    private static final ObstacleType TYPE = ObstacleType.HEIGHT;

    private Integer limit;
    private HeightProfile profile;
    private Integer range;
    private HeightObstacleType heightObstacleType;

    @PersistenceConstructor
    public HeightObstacle(String name, String city, Double latitude, Double longitude, boolean immovable,
                          Double milestone, String url, String comment, ObstacleType obstacleType, Road road,
                          Integer limit, HeightProfile profile, Integer range, HeightObstacleType heightObstacleType) {
        super(name, city, latitude, longitude, immovable, milestone, url, comment, obstacleType, road);
        this.limit = limit;
        this.profile = profile;
        this.range = range;
        this.heightObstacleType = heightObstacleType;
    }

    public HeightObstacle(Obstacle obstacle, Integer limit, HeightProfile profile, Integer range,
                          HeightObstacleType heightObstacleType) {
        super(obstacle.getName(),
              obstacle.getCity(),
              obstacle.getLatitude(),
              obstacle.getLongitude(),
              obstacle.isImmovable(),
              obstacle.getMilestone(),
              obstacle.getUrl(),
              obstacle.getComment(),
              TYPE,
              obstacle.getRoad());
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
