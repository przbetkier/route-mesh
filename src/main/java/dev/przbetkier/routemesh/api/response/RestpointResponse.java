package dev.przbetkier.routemesh.api.response;

import dev.przbetkier.routemesh.domain.restpoint.RestpointType;

public class RestpointResponse {

    private final RestpointType restpointType;
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
    private final SimpleNode node;

    public RestpointResponse(RestpointType restpointType, String roadNumber, Double milestone,
                             Integer generalSlots, Double occupancy, Integer slotLength, Integer slotWidth,
                             Integer hazardousSlots, Integer oversizeLength, Integer oversizeWidth, boolean security,
                             boolean cctv, boolean barriers, boolean lighting, SimpleNode node) {
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
        this.node = node;
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

    public SimpleNode getNode() {
        return node;
    }

    public static class SimpleNode {
        private final Long id;
        private final String name;
        private final Double latitude;
        private final Double longitude;

        public SimpleNode(Long id, String name, Double latitude, Double longitude) {
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

}
