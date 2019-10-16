package dev.przbetkier.routemesh.domain.node;

import dev.przbetkier.routemesh.domain.road.Road;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Collections;
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


    public Node() {
    }

    Node(String name, Double latitude, Double longitude, Set<Road> startRoads, Set<Road> endRoads) {
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
}
