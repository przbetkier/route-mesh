package dev.przbetkier.routemesh.domain.road.traffic;

import dev.przbetkier.routemesh.configuration.TrafficClientProperties;
import dev.przbetkier.routemesh.domain.road.RoadCords;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
class TrafficClient {

    private static final Logger logger = LoggerFactory.getLogger(TrafficClient.class);

    private final RestTemplate restTemplate;
    private final TrafficClientProperties properties;

    public TrafficClient(@Qualifier("trafficRestTemplate") RestTemplate restTemplate,
                         TrafficClientProperties properties) {
        this.restTemplate = restTemplate;
        this.properties = properties;
    }

    protected Optional<TrafficResponse> getTraffic(RoadCords cords) {

        String origin = List.of(cords.getStartLatitude(), cords.getStartLongitude())
                .stream()
                .map(Object::toString)
                .collect(Collectors.joining(", "));

        String destination = List.of(cords.getEndLatitude(), cords.getEndLongitude())
                .stream()
                .map(Object::toString)
                .collect(Collectors.joining(", "));

        URI uri = UriComponentsBuilder.fromUriString(properties.getUrl())
                .path("/maps/api/distancematrix/json")
                .queryParam("origins", origin)
                .queryParam("destinations", destination)
                .queryParam("departure_time", "now")
                .queryParam("key", properties.getApiKey())
                .build()
                .toUri();

        logger.info("Requesting for road traffic with uri: {}", uri);

        try {
            return Optional.ofNullable(restTemplate.getForObject(uri, TrafficResponse.class));
        } catch (RestClientException exception) {
            logger.error("Error occurred while requesting for traffic for road [{}]", cords.getRoadId());
            return Optional.empty();
        }
    }
}
