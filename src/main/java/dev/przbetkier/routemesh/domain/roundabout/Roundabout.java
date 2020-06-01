package dev.przbetkier.routemesh.domain.roundabout;

import dev.przbetkier.routemesh.domain.node.Node;
import dev.przbetkier.routemesh.domain.road.Road;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

@NodeEntity
public class Roundabout {

    @Id
    @GeneratedValue
    private Long id;
    private Integer innerDiameter;
    private Integer outerDiameter;

    @Relationship(type = "EXIT")
    private Set<Exit> exits = new HashSet<>();

    @Relationship(type = "IS_ROUNDABOUT", direction = Relationship.INCOMING)
    private Node node;

    public Roundabout() {
    }

    public Roundabout(Integer innerDiameter, Integer outerDiameter, HashSet<Exit> exits) {
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

    public Set<Exit> getExits() {
        return exits;
    }

    public Node getNode() {
        return node;
    }

    public void addExit(Road road, Integer startAngle, Integer endAngle) {
        this.exits.add(new Exit(this, road, startAngle, endAngle));
    }
}
