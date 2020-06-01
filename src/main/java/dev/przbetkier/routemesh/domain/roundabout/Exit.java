package dev.przbetkier.routemesh.domain.roundabout;

import dev.przbetkier.routemesh.domain.road.Road;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type = "EXIT")
public class Exit {

    @Id
    @GeneratedValue
    private Long id;

    @StartNode
    private Roundabout roundabout;

    @EndNode
    private Road road;

    private Integer startAngle;
    private Integer endAngle;

    public Exit() {
    }

    public Exit(Roundabout roundabout, Road road, Integer startAngle, Integer endAngle) {
        this.roundabout = roundabout;
        this.road = road;
        this.startAngle = startAngle;
        this.endAngle = endAngle;
    }

    public Long getId() {
        return id;
    }

    public Roundabout getRoundabout() {
        return roundabout;
    }

    public Road getRoad() {
        return road;
    }

    public Integer getStartAngle() {
        return startAngle;
    }

    public Integer getEndAngle() {
        return endAngle;
    }
}
