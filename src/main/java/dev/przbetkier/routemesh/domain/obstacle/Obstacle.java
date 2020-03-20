package dev.przbetkier.routemesh.domain.obstacle;

import dev.przbetkier.routemesh.domain.road.Road;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class Obstacle {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Double latitude;
    private Double longitude;

    @Relationship(type = "OBSTRUCTS")
    private Road road;

    public Obstacle(String name, Double latitude, Double longitude, Road road) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.road = road;
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

    public Road getRoad() {
        return road;
    }
}
