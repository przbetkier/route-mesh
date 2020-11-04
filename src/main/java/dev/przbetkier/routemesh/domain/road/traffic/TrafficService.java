package dev.przbetkier.routemesh.domain.road.traffic;

import dev.przbetkier.routemesh.domain.road.RoadCords;
import dev.przbetkier.routemesh.domain.road.RoadsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static java.math.RoundingMode.CEILING;

@Service
public class TrafficService {

    private static final Logger logger = LoggerFactory.getLogger(TrafficService.class);

    private final RoadsService roadsService;
    private final TrafficClient trafficClient;

    public TrafficService(RoadsService roadsService, TrafficClient trafficClient) {
        this.roadsService = roadsService;
        this.trafficClient = trafficClient;
    }

    public void getTraffic() {
        List<RoadCords> roadsWithCords = roadsService.getAllRoadsWithCords();
        logger.info("Started requesting for traffic for {} roads.", roadsWithCords.size());
        roadsWithCords.forEach(r -> trafficClient.getTraffic(r)
                .map(response -> response.getRows().get(0).getElements().get(0))
                .map(element -> BigDecimal.valueOf(element.getDurationInTraffic().getValue())
                        .divide(BigDecimal.valueOf(element.getDuration().getValue()), 4, CEILING)
                        .doubleValue())
                .ifPresent(factor -> {
                    logger.info("Traffic factor is: {}", factor);
                    roadsService.setTraffic(r.getRoadId(), factor);
                }));
    }
}
