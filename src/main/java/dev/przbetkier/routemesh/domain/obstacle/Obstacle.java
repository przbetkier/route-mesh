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
    private String city;
    private Double latitude;
    private Double longitude;
    private boolean immovable;
    // Defines exact km value on the road which is obstructed
    private Double milestone;
    private String url;
    private String comment;
    private ObstacleType type;
    private ObstacleSubtype subtype;

    @Relationship(type = "OBSTRUCTS")
    private Road road;

    public Obstacle() {
    }

    public Obstacle(String name, String city, Double latitude, Double longitude, boolean immovable, Double milestone,
                    String url, String comment, ObstacleType type, ObstacleSubtype subtype, Road road) {
        this.name = name;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
        this.immovable = immovable;
        this.milestone = milestone;
        this.url = url;
        this.comment = comment;
        this.type = type;
        this.subtype = subtype;
        this.road = road;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public boolean isImmovable() {
        return immovable;
    }

    public Double getMilestone() {
        return milestone;
    }

    public String getUrl() {
        return url;
    }

    public String getComment() {
        return comment;
    }

    public ObstacleType getType() {
        return type;
    }

    public ObstacleSubtype getSubtype() {
        return subtype;
    }

    public Road getRoad() {
        return road;
    }
}
