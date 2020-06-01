package dev.przbetkier.routemesh.api;

import dev.przbetkier.routemesh.api.request.RoundAboutRequest;
import dev.przbetkier.routemesh.api.response.RoundaboutResponse;
import dev.przbetkier.routemesh.domain.roundabout.RoundaboutService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/roundabouts")
class RoundaboutEndpoint {

    private static final Logger logger = LoggerFactory.getLogger(RoundaboutEndpoint.class);

    private final RoundaboutService roundaboutService;

    RoundaboutEndpoint(RoundaboutService roundaboutService) {
        this.roundaboutService = roundaboutService;
    }

    @GetMapping("/{id}")
    public RoundaboutResponse getRoundabout(@PathVariable Long id) {
        return RoundaboutResponse.fromRoundabout(roundaboutService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoundabout(@PathVariable Long id) {
        logger.info("Received request to delete roundabout id: [{}]", id);
        roundaboutService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<RoundaboutResponse> getRoundabouts() {
        return roundaboutService.getAll().stream().map(RoundaboutResponse::fromRoundabout).collect(Collectors.toList());
    }

    @PostMapping
    public void create(@RequestBody RoundAboutRequest request) {
        roundaboutService.handleRoundaboutRequest(request);
    }
}
