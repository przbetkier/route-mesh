package dev.przbetkier.routemesh.domain.obstacle.obstructions;

import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class ElevationObstruction extends Obstruction {

    private Integer verticalCurveRadius;

    public ElevationObstruction() {
    }

    public ElevationObstruction(Integer verticalCurveRadius) {
        this.verticalCurveRadius = verticalCurveRadius;
    }

    public Integer getVerticalCurveRadius() {
        return verticalCurveRadius;
    }
}
