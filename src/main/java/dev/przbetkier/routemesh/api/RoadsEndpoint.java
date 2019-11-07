package dev.przbetkier.routemesh.api;

import dev.przbetkier.routemesh.api.request.RoadRequest;
import dev.przbetkier.routemesh.api.response.RoadResponse;
import dev.przbetkier.routemesh.domain.road.Road;
import dev.przbetkier.routemesh.domain.road.RoadsService;
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

import static org.springframework.http.HttpStatus.CREATED;

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

    @PostMapping
    public ResponseEntity<RoadResponse> addRoad(@RequestBody RoadRequest roadRequest) {
        Road road = roadsService.addRoad(roadRequest);
        return new ResponseEntity<>(RoadResponse.fromRoad(road), CREATED);
    }

    @DeleteMapping("/{roadId}")
    public ResponseEntity deleteRoad(@PathVariable Long roadId) {
        roadsService.delete(roadId);
        return ResponseEntity.ok().build();
    }
}
