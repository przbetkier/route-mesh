package dev.przbetkier.routemesh.domain.restpoint;

import dev.przbetkier.routemesh.api.request.RestPointRequest;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class RestPoint {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private Double latitude;
    private Double longitude;
    // Defines exact km value on the road where the rest point is present
    private Double milestone;
    private Double width;
    private Double length;

    public RestPoint() {
    }

    public RestPoint(Long id, String name, Double latitude, Double longitude, Double milestone, Double width,
                     Double length) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.milestone = milestone;
        this.width = width;
        this.length = length;
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

    public Double getMilestone() {
        return milestone;
    }

    public Double getWidth() {
        return width;
    }

    public Double getLength() {
        return length;
    }

    public static RestPoint fromRestPointRequest(RestPointRequest request) {
        return new RestPoint(null,
                             request.getName(),
                             request.getLatitude(),
                             request.getLongitude(),
                             request.getMilestone(),
                             request.getWidth(),
                             request.getLength());
    }
}
