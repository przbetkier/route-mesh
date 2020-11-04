package dev.przbetkier.routemesh.domain.road.traffic;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
class TrafficResponse {

    private final List<TrafficRow> rows;

    @JsonCreator
    public TrafficResponse(@JsonProperty("rows") List<TrafficRow> rows) {
        this.rows = rows;
    }

    public List<TrafficRow> getRows() {
        return rows;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    static class TrafficRow {

        private final List<TrafficElement> elements;

        @JsonCreator
        public TrafficRow(@JsonProperty("elements") List<TrafficElement> elements) {
            this.elements = elements;
        }

        public List<TrafficElement> getElements() {
            return elements;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    static class TrafficElement {

        private final TrafficRecord duration;
        private final TrafficRecord durationInTraffic;

        @JsonCreator
        public TrafficElement(@JsonProperty("duration") TrafficRecord duration,
                              @JsonProperty("duration_in_traffic") TrafficRecord durationInTraffic) {
            this.duration = duration;
            this.durationInTraffic = durationInTraffic;
        }

        public TrafficRecord getDuration() {
            return duration;
        }

        public TrafficRecord getDurationInTraffic() {
            return durationInTraffic;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    static class TrafficRecord {

        private final Integer value;

        @JsonCreator
        public TrafficRecord(@JsonProperty("value") Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }
    }
}
