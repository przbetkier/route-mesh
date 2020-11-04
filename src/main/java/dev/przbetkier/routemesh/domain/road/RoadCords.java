package dev.przbetkier.routemesh.domain.road;

import org.springframework.data.neo4j.annotation.QueryResult;

@QueryResult
public class RoadCords {

    private final Long roadId;
    private final Double startLatitude;
    private final Double startLongitude;
    private final Double endLatitude;
    private final Double endLongitude;

    public RoadCords(Long roadId, Double startLatitude, Double startLongitude, Double endLatitude,
                     Double endLongitude) {
        this.roadId = roadId;
        this.startLatitude = startLatitude;
        this.startLongitude = startLongitude;
        this.endLatitude = endLatitude;
        this.endLongitude = endLongitude;
    }

    public Long getRoadId() {
        return roadId;
    }

    public Double getStartLatitude() {
        return startLatitude;
    }

    public Double getStartLongitude() {
        return startLongitude;
    }

    public Double getEndLatitude() {
        return endLatitude;
    }

    public Double getEndLongitude() {
        return endLongitude;
    }
}

