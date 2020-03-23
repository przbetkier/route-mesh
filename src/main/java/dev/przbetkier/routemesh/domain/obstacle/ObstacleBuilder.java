package dev.przbetkier.routemesh.domain.obstacle;

import dev.przbetkier.routemesh.domain.obstacle.obstructions.HeightObstruction;
import dev.przbetkier.routemesh.domain.obstacle.obstructions.Obstructions;
import dev.przbetkier.routemesh.domain.road.Road;

public class ObstacleBuilder {

    private String name;
    private String city;
    private Double latitude;
    private Double longitude;
    private boolean immovable;
    private Double milestone;
    private String url;
    private String comment;
    private Road road;
    private HeightObstruction heightObstruction;

    public static ObstacleBuilder builder() {
        return new ObstacleBuilder();
    }

    public ObstacleBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ObstacleBuilder withCity(String city) {
        this.city = city;
        return this;
    }

    public ObstacleBuilder withLatitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }

    public ObstacleBuilder withLongitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }

    public ObstacleBuilder immovable(boolean immovable) {
        this.immovable = immovable;
        return this;
    }

    public ObstacleBuilder withMilestone(Double milestone) {
        this.milestone = milestone;
        return this;
    }

    public ObstacleBuilder withUrl(String url) {
        this.url = url;
        return this;
    }

    public ObstacleBuilder withComment(String comment) {
        this.comment = comment;
        return this;
    }

    public ObstacleBuilder obstructingRoad(Road road) {
        this.road = road;
        return this;
    }

    public ObstacleBuilder withHeightObstruction(HeightObstruction heightObstruction) {
        this.heightObstruction = heightObstruction;
        return this;
    }

    public Obstacle build() {
        return new Obstacle(name,
                            city,
                            latitude,
                            longitude,
                            immovable,
                            milestone,
                            url,
                            comment,
                            heightObstruction,
                            road);
    }

}
