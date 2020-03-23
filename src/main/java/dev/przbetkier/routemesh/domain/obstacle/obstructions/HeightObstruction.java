package dev.przbetkier.routemesh.domain.obstacle.obstructions;

import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class HeightObstruction extends Obstruction {

    private Integer limit;
    private HeightProfile profile;
    private Integer range;
    private ObstacleHeightSubtype subtype;

    public HeightObstruction() {
    }

    public HeightObstruction(Integer limit, HeightProfile profile, Integer range,
                             ObstacleHeightSubtype subtype) {
        this.limit = limit;
        this.profile = profile;
        this.range = range;
        this.subtype = subtype;
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

    public ObstacleHeightSubtype getSubtype() {
        return subtype;
    }
}
