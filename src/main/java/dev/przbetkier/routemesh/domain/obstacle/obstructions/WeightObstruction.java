package dev.przbetkier.routemesh.domain.obstacle.obstructions;

import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class WeightObstruction extends Obstruction {

    private Integer limit;
    private Integer mlc;
    private ObstacleWeightSubtype subtype;

    public WeightObstruction() {
    }

    public WeightObstruction(Integer limit, Integer mlc, ObstacleWeightSubtype subtype) {
        this.limit = limit;
        this.mlc = mlc;
        this.subtype = subtype;
    }

    public Integer getLimit() {
        return limit;
    }

    public Integer getMlc() {
        return mlc;
    }

    public ObstacleWeightSubtype getSubtype() {
        return subtype;
    }
}
