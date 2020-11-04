package dev.przbetkier.routemesh.domain.road.traffic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
// FIXME: Test this component
class TrafficScheduler {

    private static final Logger logger = LoggerFactory.getLogger(TrafficScheduler.class);

    private final TrafficService trafficService;

    public TrafficScheduler(TrafficService trafficService) {
        this.trafficService = trafficService;
    }

    @Scheduled(cron = "0 0 0 * * *")
    void getTrafficScheduled() {
        logger.info("Starting traffic scheduler.");
        trafficService.getTraffic();
        logger.info("Finished traffic job.");
    }
}
