package dev.przbetkier.routemesh.api;

import dev.przbetkier.routemesh.api.request.RestPointRequest;
import dev.przbetkier.routemesh.api.response.RestpointResponse;
import dev.przbetkier.routemesh.domain.restpoint.RestPoint;
import dev.przbetkier.routemesh.domain.restpoint.RestPointRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

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
        RestPoint restPoint = restPointRepository.save(RestPoint.fromRestpointRequest(request));
        logger.info("Created restpoint with ID: [{}]", restPoint.getId());
        restPointRepository.linkRestPointToNode(restPoint.getId(), request.getNodeId());
        logger.info("Linked restpoint: [{}] with node: [{}]", restPoint.getId(), request.getNodeId());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestpoint(@PathVariable Long id) {
        logger.info("Received request to delete restpoint id: [{}]", id);
        restPointRepository.deleteRestpoint(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<RestpointResponse>> getAllRestpoints() {
        logger.info("Received request to get all restpoints");
        var restpoints = restPointRepository.findAll()
                .stream()
                .map(r -> new RestpointResponse(r.getId(), r.getRestpointType(),
                                                r.getRoadNumber(),
                                                r.getMilestone(),
                                                r.getGeneralSlots(),
                                                r.getOccupancy(),
                                                r.getSlotLength(),
                                                r.getSlotWidth(),
                                                r.getHazardousSlots(),
                                                r.getOversizeLength(),
                                                r.getOversizeWidth(),
                                                r.isSecurity(),
                                                r.isCctv(),
                                                r.isBarriers(),
                                                r.isLighting(),
                                                new RestpointResponse.SimpleNode(r.getNode().getId(),
                                                                                 r.getNode().getName(),
                                                                                 r.getNode().getLatitude(),
                                                                                 r.getNode().getLongitude())))
                .collect(Collectors.toList());
        return ResponseEntity.ok(restpoints);
    }
}
