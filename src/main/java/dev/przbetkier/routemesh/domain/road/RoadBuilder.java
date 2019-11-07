package dev.przbetkier.routemesh.domain.road;

import dev.przbetkier.routemesh.domain.admin.Admin;
import dev.przbetkier.routemesh.domain.node.Node;

import java.util.Set;
import java.util.TreeSet;

public class RoadBuilder {

    private String name;
    private Node startNode;
    private Node endNode;
    private RoadDirection direction;
    private Set<Admin> admins;
    private RoadType type;
    private Set<String> numbers;
    private TreeSet<Double> kmRange;
    private Integer lines;
    private Double maxAxleLoad;
    private Double trafficFactor;
    private Integer width;

    public static RoadBuilder builder() {
        return new RoadBuilder();
    }

    public RoadBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public RoadBuilder withStartNode(Node node) {
        this.startNode = node;
        return this;
    }

    public RoadBuilder withEndNode(Node node) {
        this.endNode = node;
        return this;
    }

    public RoadBuilder withDirection(RoadDirection direction) {
        this.direction = direction;
        return this;
    }

    public RoadBuilder withAdmins(Set<Admin> admins) {
        this.admins = admins;
        return this;
    }

    public RoadBuilder withType(RoadType type) {
        this.type = type;
        return this;
    }

    public RoadBuilder withNumbers(Set<String> numbers) {
        this.numbers = numbers;
        return this;
    }

    public RoadBuilder withKmRange(TreeSet<Double> kmRange) {
        this.kmRange = kmRange;
        return this;
    }

    public RoadBuilder withLines(Integer lines) {
        this.lines = lines;
        return this;
    }

    public RoadBuilder withMaxAxleLoad(Double maxAxleLoad) {
        this.maxAxleLoad = maxAxleLoad;
        return this;
    }

    public RoadBuilder withTrafficFactor(Double trafficFactor) {
        this.trafficFactor = trafficFactor;
        return this;
    }

    public RoadBuilder withWidth(Integer width) {
        this.width = width;
        return this;
    }

    public Road build() {
        return new Road(name,
                        startNode,
                        endNode,
                        direction,
                        admins,
                        type,
                        numbers,
                        kmRange,
                        lines,
                        maxAxleLoad,
                        trafficFactor,
                        width);
    }
}
