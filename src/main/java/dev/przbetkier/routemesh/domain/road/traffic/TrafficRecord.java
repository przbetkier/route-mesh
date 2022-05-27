package dev.przbetkier.routemesh.domain.road.traffic;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document
public class TrafficRecord {

    @Id
    private final String id;
    private final Instant date;
    private final Long roadId;
    private final Double trafficFactor;

    private final RoadNode startNode;
    private final RoadNode endNode;

    public TrafficRecord(String id, Instant date, Long roadId, Double trafficFactor, RoadNode startNode, RoadNode endNode) {
        this.id = id;
        this.date = date;
        this.roadId = roadId;
        this.trafficFactor = trafficFactor;
        this.startNode = startNode;
        this.endNode = endNode;
    }

    public String getId() {
        return id;
    }

    public Instant getDate() {
        return date;
    }

    public Long getRoadId() {
        return roadId;
    }

    public Double getTrafficFactor() {
        return trafficFactor;
    }

    public RoadNode getStartNode() {
        return startNode;
    }

    public RoadNode getEndNode() {
        return endNode;
    }
}
