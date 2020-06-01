package dev.przbetkier.routemesh.domain.roundabout;

import dev.przbetkier.routemesh.api.request.RoundAboutRequest;
import dev.przbetkier.routemesh.domain.common.EntityNotFoundException;
import dev.przbetkier.routemesh.domain.road.RoadsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

@Service
public class RoundaboutService {

    private static final Logger logger = LoggerFactory.getLogger(RoundaboutService.class);

    private final RoundaboutRepository roundaboutRepository;
    private final RoadsService roadsService;

    RoundaboutService(RoundaboutRepository roundaboutRepository, RoadsService roadsService) {
        this.roundaboutRepository = roundaboutRepository;
        this.roadsService = roadsService;
    }

    public Roundabout getById(Long id) {
        return roundaboutRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("roundabout", id));
    }

    public List<Roundabout> getAll() {
        return roundaboutRepository.findAll();
    }

    public void deleteById(Long id) {
        roundaboutRepository.deleteRoundabout(id);
        logger.info("Detached and deleted roundabout id: [{}]", id);
    }

    @Transactional
    public void handleRoundaboutRequest(RoundAboutRequest request) {
        logger.info("Received request for creating a roundabout for node {}.", request.getNodeId());

        Roundabout roundabout = createAndSaveRoundabout(request);
        roundaboutRepository.linkToNode(roundabout.getId(), request.getNodeId());
    }

    private Roundabout createAndSaveRoundabout(RoundAboutRequest request) {
        Roundabout roundabout = new Roundabout(request.getInnerDiameter(), request.getOuterDiameter(), new HashSet<>());
        request.getExitRequests()
                .forEach(exitRequest -> roadsService.getById(exitRequest.getRoadId())
                        .ifPresent(r -> roundabout.addExit(r,
                                                           exitRequest.getStartAngle(),
                                                           exitRequest.getEndAngle())));
        return roundaboutRepository.save(roundabout);
    }
}
