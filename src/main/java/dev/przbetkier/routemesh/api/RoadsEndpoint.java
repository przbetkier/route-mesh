package dev.przbetkier.routemesh.api;

import dev.przbetkier.routemesh.api.request.RoadRequest;
import dev.przbetkier.routemesh.api.response.RoadResponse;
import dev.przbetkier.routemesh.domain.road.Road;
import dev.przbetkier.routemesh.domain.road.RoadDirection;
import dev.przbetkier.routemesh.domain.road.RoadsService;
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
@RequestMapping("/roads")
class RoadsEndpoint {

    private final RoadsService roadsService;

    RoadsEndpoint(RoadsService roadsService) {
        this.roadsService = roadsService;
    }

    @GetMapping
    public List<RoadResponse> getAll() {
        return roadsService.getAll().stream().map(RoadResponse::fromRoad).collect(Collectors.toList());
    }

    @GetMapping("/one-way")
    public List<RoadResponse> getOneWayRoads() {
        return roadsService
                .getAllByType(RoadDirection.ONE_WAY)
                .stream()
                .map(RoadResponse::fromRoad)
                .collect(Collectors.toList());
    }

    @GetMapping("/two-way")
    public List<RoadResponse> getTwoWayRoads() {
        return roadsService
                .getAllByType(RoadDirection.TWO_WAY)
                .stream()
                .map(RoadResponse::fromRoad)
                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity addNewRoad(@RequestBody RoadRequest roadRequest) {
        Road road = roadsService.addRoad(roadRequest);
        return ResponseEntity.ok(RoadResponse.fromRoad(road));
    }

    @DeleteMapping("/{roadId}")
    public ResponseEntity deleteRoad(@PathVariable Long roadId) {
        roadsService.delete(roadId);
        return ResponseEntity.ok().build();
    }
}
