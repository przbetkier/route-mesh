package dev.przbetkier.routemesh.domain.road.traffic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
class TrafficScheduler {
    private static final Logger logger = LoggerFactory.getLogger(TrafficScheduler.class);

    private final TrafficService trafficService;
    private final boolean trafficSchedulerEnabled;

    public TrafficScheduler(TrafficService trafficService,
                            @Value("${traffic.scheduler.enabled}") boolean trafficSchedulerEnabled) {
        this.trafficService = trafficService;
        this.trafficSchedulerEnabled = trafficSchedulerEnabled;
    }

    // Every 30 min
    @Scheduled(initialDelay = 1000, fixedDelay = 1800000)
    void getTrafficScheduled() {
        if (trafficSchedulerEnabled) {
            logger.info("Starting traffic scheduler.");
            trafficService.getTraffic();
            logger.info("Finished traffic scheduler.");
        } else {
            logger.info("Traffic scheduler is disabled. Roads traffic factors were not updated.");
        }
    }
}
