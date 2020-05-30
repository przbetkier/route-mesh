package dev.przbetkier.routemesh.domain.roundabout;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.ArrayList;
import java.util.List;

@NodeEntity
public class Roundabout {

    @Id
    @GeneratedValue
    private Long id;
    private Integer innerDiameter;
    private Integer outerDiameter;

    @Relationship(type = "EXIT")
    private ArrayList<Exit> exits = new ArrayList<>();

    public Roundabout() {
    }

    public Roundabout(Integer innerDiameter, Integer outerDiameter, ArrayList<Exit> exits) {
        this.innerDiameter = innerDiameter;
        this.outerDiameter = outerDiameter;
        this.exits = exits;
    }

    public Long getId() {
        return id;
    }

    public Integer getInnerDiameter() {
        return innerDiameter;
    }

    public Integer getOuterDiameter() {
        return outerDiameter;
    }

    public List<Exit> getExits() {
        return exits;
    }
}
