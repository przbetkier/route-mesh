package dev.przbetkier.routemesh.domain.obstacle.obstructions;

import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class CurvatureObstruction extends Obstruction {

    private Integer innerRadius;
    private Integer outerRadius;
    /** Radius of the horizontal arc on which removable object exists */
    private Integer boundaryRadius;

    public CurvatureObstruction() {
    }

    public CurvatureObstruction(Integer innerRadius, Integer outerRadius, Integer boundaryRadius) {
        this.innerRadius = innerRadius;
        this.outerRadius = outerRadius;
        this.boundaryRadius = boundaryRadius;
    }

    public Integer getInnerRadius() {
        return innerRadius;
    }

    public Integer getOuterRadius() {
        return outerRadius;
    }

    public Integer getBoundaryRadius() {
        return boundaryRadius;
    }
}
