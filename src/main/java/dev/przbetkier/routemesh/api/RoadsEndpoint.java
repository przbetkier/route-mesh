package dev.przbetkier.routemesh.api;

import dev.przbetkier.routemesh.api.request.RoadRequest;
import dev.przbetkier.routemesh.api.response.RoadResponse;
import dev.przbetkier.routemesh.domain.road.Road;
import dev.przbetkier.routemesh.domain.road.RoadsService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/roads")
class RoadsEndpoint {

    private final RoadsService roadsService;

    RoadsEndpoint(RoadsService roadsService) {
        this.roadsService = roadsService;
    }

    @GetMapping
    public Page<RoadResponse> getAll(@RequestParam int page,
                                     @RequestParam(required = false, defaultValue = "20") int size,
                                     @RequestParam(required = false) String query) {
        return roadsService.getAll(page, size, query).map(RoadResponse::fromRoad);
    }

    @GetMapping("/{roadId}")
    public ResponseEntity<RoadResponse> getById(@PathVariable Long roadId) {
        return roadsService.getById(roadId)
                .map(r -> new ResponseEntity<>(RoadResponse.fromRoad(r), OK))
                .orElseGet(() -> new ResponseEntity<>(NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<RoadResponse> addRoad(@RequestBody RoadRequest roadRequest) {
        Road road = roadsService.addRoad(roadRequest);
        return new ResponseEntity<>(RoadResponse.fromRoad(road), CREATED);
    }

    @DeleteMapping("/{roadId}")
    public ResponseEntity<Void> deleteRoad(@PathVariable Long roadId) {
        roadsService.delete(roadId);
        return ResponseEntity.ok().build();
    }
}
