package dev.przbetkier.routemesh.domain.obstacle.obstructions;

import org.neo4j.ogm.annotation.NodeEntity;

import java.util.List;

@NodeEntity
public class WidthObstruction extends Obstruction {

    // Obstruction limits measured between road center and obstruction
    // Can be negative if passes road center
    private List<Integer> limits;
    // Ranges defined how tall width obstruction are
    private List<Integer> ranges;
    private ObstacleWidthSubtype subtype;
    // Defines if obstruction exists symmetrically on both sides (eg tunnel)
    private boolean symmetric;

    public WidthObstruction() {
    }

    public WidthObstruction(List<Integer> limits, List<Integer> ranges, ObstacleWidthSubtype subtype,
                            boolean symmetric) {
        this.limits = limits;
        this.ranges = ranges;
        this.subtype = subtype;
        this.symmetric = symmetric;
    }

    public List<Integer> getLimits() {
        return limits;
    }

    public List<Integer> getRanges() {
        return ranges;
    }

    public ObstacleWidthSubtype getSubtype() {
        return subtype;
    }

    public boolean isSymmetric() {
        return symmetric;
    }
}
