package dev.przbetkier.routemesh.api.request;

import dev.przbetkier.routemesh.domain.restpoint.RestpointType;

public class RestPointRequest {

    private final RestpointType restpointType;
    private final Long nodeId;
    private final String roadNumber;
    private final Double milestone;
    private final Integer generalSlots;
    // From 0..1
    private final Double occupancy;
    private final Integer slotLength;
    private final Integer slotWidth;
    private final Integer hazardousSlots;
    private final Integer oversizeLength;
    private final Integer oversizeWidth;
    private final boolean security;
    private final boolean cctv;
    private final boolean barriers;
    private final boolean lighting;

    public RestPointRequest(RestpointType restpointType, Long nodeId, String roadNumber, Double milestone,
                            Integer generalSlots, Double occupancy, Integer slotLength, Integer slotWidth,
                            Integer hazardousSlots, Integer oversizeLength, Integer oversizeWidth, boolean security,
                            boolean cctv, boolean barriers, boolean lighting) {
        this.restpointType = restpointType;
        this.nodeId = nodeId;
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

    public RestpointType getRestpointType() {
        return restpointType;
    }

    public Long getNodeId() {
        return nodeId;
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
}
