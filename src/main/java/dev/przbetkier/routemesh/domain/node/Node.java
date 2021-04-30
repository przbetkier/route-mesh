package dev.przbetkier.routemesh.domain.node;

import dev.przbetkier.routemesh.domain.restpoint.RestPoint;
import dev.przbetkier.routemesh.domain.road.Road;
import dev.przbetkier.routemesh.domain.roundabout.Roundabout;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

@NodeEntity
public class Node {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Double latitude;
    private Double longitude;
    @Relationship(type = "STARTS")
    private Set<Road> startRoads = Collections.emptySet();
    @Relationship(type = "ENDS", direction = Relationship.INCOMING)
    private Set<Road> endRoads = Collections.emptySet();

    @Relationship(type = "IS_ROUNDABOUT")
    private Roundabout roundabout;

    @Relationship(type = "IS_RESTPOINT")
    private RestPoint restpoint;

    public Node() {
    }

    public Node(String name, Double latitude, Double longitude, Set<Road> startRoads, Set<Road> endRoads) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.startRoads = startRoads;
        this.endRoads = endRoads;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Set<Road> getStartRoads() {
        return startRoads;
    }

    public Set<Road> getEndRoads() {
        return endRoads;
    }

    public NodeType getType() {
        return Objects.isNull(roundabout) ? NodeType.INTERSECTION : NodeType.ROUNDABOUT;
    }

    public boolean isRestpoint() {
        return !Objects.isNull(restpoint);
    }

    public Roundabout getRoundabout() {
        return roundabout;
    }

    public RestPoint getRestpoint() {
        return restpoint;
    }
}
