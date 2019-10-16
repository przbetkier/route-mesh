package dev.przbetkier.routemesh.api.request;

import dev.przbetkier.routemesh.domain.road.RoadDirection;
import dev.przbetkier.routemesh.domain.road.RoadType;

import java.util.List;

public class RoadRequest {

    private final String name;
    private final Long startNode;
    private final Long endNode;
    private final RoadDirection direction;
    private final RoadType type;
    private final Integer lines;
    private final List<String> numbers;
    private final List<Double> kmRange;
    private final Double maxAxleLoad;
    private final Integer width;
    private final List<Long> admins;

    public RoadRequest(String name, Long startNode, Long endNode, RoadDirection direction, RoadType type, Integer lines,
                       List<String> numbers, List<Double> kmRange, Double maxAxleLoad, Integer width, List<Long> admins) {
        this.name = name;
        this.startNode = startNode;
        this.endNode = endNode;
        this.direction = direction;
        this.type = type;
        this.lines = lines;
        this.numbers = numbers;
        this.kmRange = kmRange;
        this.maxAxleLoad = maxAxleLoad;
        this.width = width;
        this.admins = admins;
    }

    public String getName() {
        return name;
    }

    public Long getStartNode() {
        return startNode;
    }

    public Long getEndNode() {
        return endNode;
    }

    public RoadDirection getDirection() {
        return direction;
    }

    public RoadType getType() {
        return type;
    }

    public Integer getLines() {
        return lines;
    }

    public List<String> getNumbers() {
        return numbers;
    }

    public List<Double> getKmRange() {
        return kmRange;
    }

    public Double getMaxAxleLoad() {
        return maxAxleLoad;
    }

    public Integer getWidth() {
        return width;
    }

    public List<Long> getAdmins() {
        return admins;
    }
}
