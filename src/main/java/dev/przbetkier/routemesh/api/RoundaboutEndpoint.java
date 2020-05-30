package dev.przbetkier.routemesh.api;

import dev.przbetkier.routemesh.api.request.RoundAboutRequest;
import dev.przbetkier.routemesh.domain.roundabout.RoundaboutService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roundabouts")
class RoundaboutEndpoint {

    private final RoundaboutService roundaboutService;

    RoundaboutEndpoint(RoundaboutService roundaboutService) {
        this.roundaboutService = roundaboutService;
    }

    @PostMapping
    public void create(@RequestBody RoundAboutRequest request) {
        roundaboutService.handleRoundaboutRequest(request);
    }
}
