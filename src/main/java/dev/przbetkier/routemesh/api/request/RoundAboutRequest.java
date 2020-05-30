package dev.przbetkier.routemesh.api.request;

import java.util.Set;

public class RoundAboutRequest {

    private final Long nodeId;
    private final Integer innerDiameter;
    private final Integer outerDiameter;
    private final Set<ExitRequest> exitRequests;

    public RoundAboutRequest(Long nodeId, Integer innerDiameter, Integer outerDiameter, Set<ExitRequest> exitRequests) {
        this.nodeId = nodeId;
        this.innerDiameter = innerDiameter;
        this.outerDiameter = outerDiameter;
        this.exitRequests = exitRequests;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public Integer getInnerDiameter() {
        return innerDiameter;
    }

    public Integer getOuterDiameter() {
        return outerDiameter;
    }

    public Set<ExitRequest> getExitRequests() {
        return exitRequests;
    }

    public static class ExitRequest {

        private final Long roadId;
        private final Integer enterAngle;
        private final Integer exitAngle;

        public ExitRequest(Long roadId, Integer enterAngle, Integer exitAngle) {
            this.roadId = roadId;
            this.enterAngle = enterAngle;
            this.exitAngle = exitAngle;
        }

        public Long getRoadId() {
            return roadId;
        }

        public Integer getEnterAngle() {
            return enterAngle;
        }

        public Integer getExitAngle() {
            return exitAngle;
        }
    }
}
