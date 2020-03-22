package dev.przbetkier.routemesh.domain.road;

import dev.przbetkier.routemesh.domain.admin.Admin;
import dev.przbetkier.routemesh.domain.node.Node;
import dev.przbetkier.routemesh.domain.obstacle.Obstacle;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Set;
import java.util.TreeSet;

@NodeEntity
public class Road {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Relationship(type = "STARTS", direction = Relationship.INCOMING)
    private Node startNode;

    @Relationship(type = "ENDS")
    private Node endNode;

    private RoadDirection direction;
    @Relationship(type = "MANAGES", direction = Relationship.INCOMING)
    private Set<Admin> admins;

    @Relationship(type = "OBSTRUCTS", direction = Relationship.INCOMING)
    private Set<Obstacle> obstacles;

    private RoadType type;
    private Set<String> numbers;

    private TreeSet<Double> kmRange;
    private Integer lines;
    private Double maxAxleLoad;
    // Number 0..1 representing traffic on that road
    private Double trafficFactor;
    private Integer width;

    public Road() {
    }

    public Road(String name, Node startNode, Node endNode, RoadDirection direction, Set<Admin> admins,
                Set<Obstacle> obstacles, RoadType type, Set<String> numbers, TreeSet<Double> kmRange, Integer lines,
                Double maxAxleLoad, Double trafficFactor, Integer width) {
        this.name = name;
        this.startNode = startNode;
        this.endNode = endNode;
        this.direction = direction;
        this.admins = admins;
        this.obstacles = obstacles;
        this.type = type;
        this.numbers = numbers;
        this.kmRange = kmRange;
        this.lines = lines;
        this.maxAxleLoad = maxAxleLoad;
        this.trafficFactor = trafficFactor;
        this.width = width;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Node getStartNode() {
        return startNode;
    }

    public Node getEndNode() {
        return endNode;
    }

    public RoadDirection getDirection() {
        return direction;
    }

    public Set<Admin> getAdmins() {
        return admins;
    }

    public RoadType getType() {
        return type;
    }

    public Set<String> getNumbers() {
        return numbers;
    }

    public TreeSet<Double> getKmRange() {
        return kmRange;
    }

    public Integer getLines() {
        return lines;
    }

    public Double getMaxAxleLoad() {
        return maxAxleLoad;
    }

    public Double getTrafficFactor() {
        return trafficFactor;
    }

    public Integer getWidth() {
        return width;
    }

    public Set<Obstacle> getObstacles() {
        return obstacles;
    }
}
