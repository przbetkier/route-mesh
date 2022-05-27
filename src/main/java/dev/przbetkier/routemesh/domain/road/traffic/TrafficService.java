package dev.przbetkier.routemesh.domain.road.traffic;

import dev.przbetkier.routemesh.domain.road.RoadCords;
import dev.przbetkier.routemesh.domain.road.RoadsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.math.RoundingMode.CEILING;

@Service
public class TrafficService {

    private static final Logger logger = LoggerFactory.getLogger(TrafficService.class);

    private final RoadsService roadsService;
    private final TrafficClient trafficClient;
    private final TrafficRecordRepository trafficRecordRepository;

    public TrafficService(RoadsService roadsService, TrafficClient trafficClient, TrafficRecordRepository trafficRecordRepository) {
        this.roadsService = roadsService;
        this.trafficClient = trafficClient;
        this.trafficRecordRepository = trafficRecordRepository;
    }

    public void getTraffic() {

        Optional<RoadCords> roadsWithCords;
        Integer skip = 780;
        do {
            logger.info("Getting road for traffic. Skip {}", skip);
            roadsWithCords = roadsService.getRoadForTraffic(skip);
            roadsWithCords.ifPresent(road -> {
                logger.info("Got road with {}", road.getRoadId());
                calculateTrafficForRoad(road);
            });
            skip++;
        } while (roadsWithCords.isPresent());
    }

    private void calculateTrafficForRoad(RoadCords cords) {
        logger.info("Started requesting for traffic for roadId: {}.", cords.getRoadId());
        trafficClient.getTraffic(cords)
                .map(response -> response.getRows().get(0).getElements().get(0))
                .map(element -> BigDecimal.valueOf(element.getDurationInTraffic().getValue())
                        .divide(BigDecimal.valueOf(element.getDuration().getValue()), 4, CEILING)
                        .doubleValue())
                .ifPresent(factor -> {
                    logger.info("Traffic factor is: {}", factor);
                    trafficRecordRepository.save(
                            new TrafficRecord(
                                    UUID.randomUUID().toString(),
                                    Instant.now(),
                                    cords.getRoadId(),
                                    factor,
                                    new RoadNode(cords.getStartLatitude(), cords.getStartLongitude()),
                                    new RoadNode(cords.getEndLatitude(), cords.getEndLongitude())
                            )
                    );
                });
    }
}
