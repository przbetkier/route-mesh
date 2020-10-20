package dev.przbetkier.routemesh.domain.obstacle;

import dev.przbetkier.routemesh.domain.obstacle.obstructions.CurvatureObstruction;
import dev.przbetkier.routemesh.domain.obstacle.obstructions.ElevationObstruction;
import dev.przbetkier.routemesh.domain.obstacle.obstructions.HeightObstruction;
import dev.przbetkier.routemesh.domain.obstacle.obstructions.WeightObstruction;
import dev.przbetkier.routemesh.domain.obstacle.obstructions.WidthObstruction;
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

    @Relationship(type = "OBSTRUCTS")
    private Road road;

    @Relationship(type = "HAS")
    private HeightObstruction heightObstruction;

    @Relationship(type = "HAS")
    private WeightObstruction weightObstruction;

    @Relationship(type = "HAS")
    private WidthObstruction widthObstruction;

    @Relationship(type = "HAS")
    private ElevationObstruction elevationObstruction;

    @Relationship(type = "HAS")
    private CurvatureObstruction curvatureObstruction;

    public Obstacle() {
    }

    public Obstacle(String name, String city, Double latitude, Double longitude, boolean immovable, Double milestone,
                    String url, String comment, Road road, HeightObstruction heightObstruction,
                    WeightObstruction weightObstruction, WidthObstruction widthObstruction,
                    ElevationObstruction elevationObstruction, CurvatureObstruction curvatureObstruction) {
        this.name = name;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
        this.immovable = immovable;
        this.milestone = milestone;
        this.url = url;
        this.comment = comment;
        this.road = road;
        this.heightObstruction = heightObstruction;
        this.weightObstruction = weightObstruction;
        this.widthObstruction = widthObstruction;
        this.elevationObstruction = elevationObstruction;
        this.curvatureObstruction = curvatureObstruction;
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

    public Road getRoad() {
        return road;
    }

    public HeightObstruction getHeightObstruction() {
        return heightObstruction;
    }

    public WeightObstruction getWeightObstruction() {
        return weightObstruction;
    }

    public WidthObstruction getWidthObstruction() {
        return widthObstruction;
    }

    public ElevationObstruction getElevationObstruction() {
        return elevationObstruction;
    }

    public CurvatureObstruction getCurvatureObstruction() {
        return curvatureObstruction;
    }
}
