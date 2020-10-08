package dev.przbetkier.routemesh.api;

import dev.przbetkier.routemesh.api.request.RestPointRequest;
import dev.przbetkier.routemesh.domain.restpoint.RestPoint;
import dev.przbetkier.routemesh.domain.restpoint.RestPointRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restpoints")
class RestPointEndpoint {

    private static final Logger logger = LoggerFactory.getLogger(RestPointEndpoint.class);

    private final RestPointRepository restPointRepository;

    RestPointEndpoint(RestPointRepository restPointRepository) {
        this.restPointRepository = restPointRepository;
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public void addRestPoint(@RequestBody RestPointRequest request) {
        // FIXME: Move this logic to proper service.
        RestPoint restPoint = restPointRepository.save(RestPoint.fromRestPointRequest(request));
        logger.info("Created restpoint with ID: [{}]", restPoint.getId());
        restPointRepository.linkRestPointToRoad(restPoint.getId(), request.getRoadId());
        logger.info("Linked restpoint: [{}] with road: [{}]", restPoint.getId(), request.getRoadId());
    }
}
