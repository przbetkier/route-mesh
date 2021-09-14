package dev.przbetkier.routemesh.domain.restpoint;

import dev.przbetkier.routemesh.api.request.RestPointRequest;
import dev.przbetkier.routemesh.domain.node.Node;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class RestPoint {

    @Id
    @GeneratedValue
    private Long id;

    private RestpointType restpointType;
    private String roadNumber;
    private Double milestone;
    private Integer generalSlots;
    // From 0..1
    private Double occupancy;
    private Integer slotLength;
    private Integer slotWidth;
    private Integer hazardousSlots;
    private Integer oversizeLength;
    private Integer oversizeWidth;
    private boolean security;
    private boolean cctv;
    private boolean barriers;
    private boolean lighting;

    @Relationship(type = "IS_RESTPOINT", direction = Relationship.INCOMING)
    private Node node;

    public RestPoint() {
    }

    public RestPoint(Long id, RestpointType restpointType, String roadNumber, Double milestone, Integer generalSlots,
                     Double occupancy, Integer slotLength, Integer slotWidth, Integer hazardousSlots,
                     Integer oversizeLength, Integer oversizeWidth, boolean security, boolean cctv, boolean barriers,
                     boolean lighting) {
        this.id = id;
        this.restpointType = restpointType;
        this.roadNumber = roadNumber;
        this.milestone = milestone;
        this.generalSlots = generalSlots;
        this.occupancy = occupancy;
        this.slotLength = slotLength;
        this.slotWidth = slotWidth;
        this.hazardousSlots = hazardousSlots;
        this.oversizeLength = oversizeLength;
        this.oversizeWidth = oversizeWidth;
        this.security = security;
        this.cctv = cctv;
        this.barriers = barriers;
        this.lighting = lighting;
    }

    public Long getId() {
        return id;
    }

    public RestpointType getRestpointType() {
        return restpointType;
    }

    public String getRoadNumber() {
        return roadNumber;
    }

    public Double getMilestone() {
        return milestone;
    }

    public Integer getGeneralSlots() {
        return generalSlots;
    }

    public Double getOccupancy() {
        return occupancy;
    }

    public Integer getSlotLength() {
        return slotLength;
    }

    public Integer getSlotWidth() {
        return slotWidth;
    }

    public Integer getHazardousSlots() {
        return hazardousSlots;
    }

    public Integer getOversizeLength() {
        return oversizeLength;
    }

    public Integer getOversizeWidth() {
        return oversizeWidth;
    }

    public boolean isSecurity() {
        return security;
    }

    public boolean isCctv() {
        return cctv;
    }

    public boolean isBarriers() {
        return barriers;
    }

    public boolean isLighting() {
        return lighting;
    }

    public Node getNode() {
        return node;
    }

    public static RestPoint fromRestpointRequest(RestPointRequest request) {
        return new RestPoint(null,
                             request.getRestpointType(),
                             request.getRoadNumber(),
                             request.getMilestone(),
                             request.getGeneralSlots(),
                             request.getOccupancy(),
                             request.getSlotLength(),
                             request.getSlotWidth(),
                             request.getHazardousSlots(),
                             request.getOversizeLength(),
                             request.getOversizeWidth(),
                             request.isSecurity(),
                             request.isCctv(),
                             request.isBarriers(),
                             request.isLighting());
    }
}
