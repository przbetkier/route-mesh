package dev.przbetkier.routemesh.domain.roundabout;

import dev.przbetkier.routemesh.api.request.RoundAboutRequest;
import dev.przbetkier.routemesh.domain.node.NodeService;
import dev.przbetkier.routemesh.domain.road.RoadsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

@Service
public class RoundaboutService {

    private static final Logger logger = LoggerFactory.getLogger(RoundaboutService.class);

    private final RoundaboutRepository roundaboutRepository;
    private final RoadsService roadsService;

    RoundaboutService(RoundaboutRepository roundaboutRepository, RoadsService roadsService) {
        this.roundaboutRepository = roundaboutRepository;
        this.roadsService = roadsService;
    }

    @Transactional
    public void handleRoundaboutRequest(RoundAboutRequest request) {
        logger.info("Received request for creating a roundabout for node {}, with {} exits - roads: {}.",
                    request.getNodeId(),
                    request.getExitRequests().size(),
                    request.getExitRequests()
                            .stream()
                            .map(RoundAboutRequest.ExitRequest::getRoadId)
                            .collect(Collectors.toList()));

        Roundabout roundabout = createRoundabout(request);


        request.getExitRequests().forEach(exitRequest -> roadsService.getById(exitRequest.getRoadId()).ifPresent(r -> {
            roundaboutRepository.linkToRoad(roundabout.getId(),
                                            r.getId(),
                                            exitRequest.getEnterAngle(),
                                            exitRequest.getExitAngle());
        }));

        roundaboutRepository.linkToNode(roundabout.getId(), request.getNodeId());
    }

    private Roundabout createRoundabout(RoundAboutRequest request) {
        Roundabout roundabout = new Roundabout(request.getInnerDiameter(),
                                               request.getOuterDiameter(),
                                               new ArrayList<>());
        return roundaboutRepository.save(roundabout);
    }
}
