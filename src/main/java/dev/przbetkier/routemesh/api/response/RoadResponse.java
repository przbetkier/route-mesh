package dev.przbetkier.routemesh.api.response;

import dev.przbetkier.routemesh.domain.admin.Admin;
import dev.przbetkier.routemesh.domain.node.Node;
import dev.przbetkier.routemesh.domain.road.Road;
import dev.przbetkier.routemesh.domain.road.RoadDirection;
import dev.przbetkier.routemesh.domain.road.RoadType;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class RoadResponse {

    private final Long id;
    private final String name;
    private final SimpleNode startNode;
    private final SimpleNode endNode;
    private final RoadDirection roadDirection;
    private final RoadType type;
    private final Set<String> numbers;
    private final TreeSet<Double> kmRange;
    private final Integer lines;
    private final Double maxAxleLoad;
    private final Double trafficFactor;
    private final Set<SimpleAdmin> admins;
    private final Integer width;
    private final Set<SimpleObstacle> obstacles;

    public RoadResponse(Long id, String name, SimpleNode startNode, SimpleNode endNode, RoadDirection roadDirection,
                        RoadType type, Set<String> numbers, TreeSet<Double> kmRange, Integer lines, Double maxAxleLoad,
                        Double trafficFactor, Set<SimpleAdmin> admins, Integer width, Set<SimpleObstacle> obstacles) {
        this.id = id;
        this.startNode = startNode;
        this.endNode = endNode;
        this.name = name;
        this.roadDirection = roadDirection;
        this.type = type;
        this.numbers = numbers;
        this.kmRange = kmRange;
        this.lines = lines;
        this.maxAxleLoad = maxAxleLoad;
        this.trafficFactor = trafficFactor;
        this.admins = admins;
        this.width = width;
        this.obstacles = obstacles;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public SimpleNode getStartNode() {
        return startNode;
    }

    public SimpleNode getEndNode() {
        return endNode;
    }

    public RoadDirection getRoadDirection() {
        return roadDirection;
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

    public Set<SimpleAdmin> getAdmins() {
        return admins;
    }

    public Integer getWidth() {
        return width;
    }

    public Set<SimpleObstacle> getObstacles() {
        return obstacles;
    }

    public static RoadResponse fromRoad(Road road) {
        return new RoadResponse(road.getId(),
                                road.getName(),
                                toSimpleNode(road.getStartNode()),
                                toSimpleNode(road.getEndNode()),
                                road.getDirection(),
                                road.getType(),
                                road.getNumbers(),
                                road.getKmRange(),
                                road.getLines(),
                                road.getMaxAxleLoad(),
                                road.getTrafficFactor(),
                                hasAdmins(road) ? road.getAdmins()
                                        .stream()
                                        .map(SimpleAdmin::new)
                                        .collect(Collectors.toSet()) : Collections.emptySet(),
                                road.getWidth(),
                                hasObstacles(road) ? road.getObstacles()
                                        .stream()
                                        .map(ob -> new SimpleObstacle(ob.getId(),
                                                                      ob.getName(),
                                                                      ob.getCity(),
                                                                      ob.getMilestone(),
                                                                      ob.getLatitude(),
                                                                      ob.getLongitude(),
                                                                      ob.isImmovable(),
                                                                      ob.getComment()))
                                        .collect(Collectors.toSet()) : Collections.emptySet());
    }

    private static boolean hasAdmins(Road road) {
        return road.getAdmins() != null && !road.getAdmins().isEmpty();
    }

    private static boolean hasObstacles(Road road) {
        return road.getObstacles() != null && !road.getObstacles().isEmpty();
    }

    private static SimpleNode toSimpleNode(Node node) {
        return new SimpleNode(node.getId(), node.getName(), node.getLatitude(), node.getLongitude());
    }

    public static class SimpleNode {
        private final Long id;
        private final String name;
        private final Double latitude;
        private final Double longitude;

        SimpleNode(Long id, String name, Double latitude, Double longitude) {
            this.id = id;
            this.name = name;
            this.latitude = latitude;
            this.longitude = longitude;
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
    }

    public static class SimpleAdmin {
        private final Long id;
        private final String name;

        SimpleAdmin(Admin admin) {
            this.id = admin.getId();
            this.name = admin.getName();
        }

        public Long getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

    public static class SimpleObstacle {

        private Long id;
        private String name;
        private String city;
        private Double milestone;
        private Double latitude;
        private Double longitude;
        private Boolean immovable;
        private String comment;

        public SimpleObstacle() {
        }

        public SimpleObstacle(Long id, String name, String city, Double milestone, Double latitude, Double longitude,
                              Boolean immovable, String comment) {
            this.id = id;
            this.name = name;
            this.city = city;
            this.milestone = milestone;
            this.latitude = latitude;
            this.longitude = longitude;
            this.immovable = immovable;
            this.comment = comment;
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

        public Double getMilestone() {
            return milestone;
        }

        public Double getLatitude() {
            return latitude;
        }

        public Double getLongitude() {
            return longitude;
        }

        public Boolean getImmovable() {
            return immovable;
        }

        public String getComment() {
            return comment;
        }
    }
}
