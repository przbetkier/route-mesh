package dev.przbetkier.routemesh.domain.road;

import dev.przbetkier.routemesh.api.request.RoadRequest;
import dev.przbetkier.routemesh.domain.admin.Admin;
import dev.przbetkier.routemesh.domain.admin.AdminService;
import dev.przbetkier.routemesh.domain.node.Node;
import dev.przbetkier.routemesh.domain.node.NodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

@Service
public class RoadsService {

    private static final Logger logger = LoggerFactory.getLogger(RoadsService.class);

    private final RoadRepository roadRepository;
    private final NodeService nodeService;
    private final AdminService adminService;

    public RoadsService(RoadRepository roadRepository, NodeService nodeService, AdminService adminService) {
        this.roadRepository = roadRepository;
        this.nodeService = nodeService;
        this.adminService = adminService;
    }

    public Page<Road> getAll(int page, int size, String query) {
        return Optional.ofNullable(query).map(q -> {
            logger.info("Searching for roads with name matching query: {}", query);
            return roadRepository.findAllByNameContainingIgnoreCase(query, PageRequest.of(page, size, Sort.by("name")));
        }).orElseGet(() -> roadRepository.findAll(PageRequest.of(page, size, Sort.by("name"))));
    }

    public Optional<Road> getById(Long id) {
        logger.info("Search request for road {}", id);
        return roadRepository.findById(id);
    }

    public void delete(Long roadId) {
        logger.info("Deleting road [{}]", roadId);
        roadRepository.deleteRoadAndConnectedObstacles(roadId);
        logger.info("Road [{}] deleted", roadId);
    }

    public Road addRoad(RoadRequest roadRequest) {
        logger.info("Adding new road: [{}]", roadRequest.getName());
        Node start = nodeService.getNode(roadRequest.getStartNode());
        Node end = nodeService.getNode(roadRequest.getEndNode());
        Set<Admin> admins = new HashSet<>();
        roadRequest.getAdmins().forEach(id -> adminService.findById(id).ifPresent(admins::add));

        Road road = RoadBuilder.builder()
                .withName(roadRequest.getName())
                .withStartNode(start)
                .withEndNode(end)
                .withDirection(roadRequest.getDirection())
                .withAdmins(admins)
                .withType(roadRequest.getType())
                .withNumbers(new HashSet<>(roadRequest.getNumbers()))
                .withKmRange(new TreeSet<>(roadRequest.getKmRange()))
                .withLines(roadRequest.getLines())
                .withMaxAxleLoad(roadRequest.getMaxAxleLoad())
                .withWidth(roadRequest.getWidth())
                .build();

        return roadRepository.save(road);
    }

    public Optional<RoadCords> getRoadForTraffic(Integer skip) {
        logger.info("Retrieving all roads with cords.");
        return roadRepository.getRoadForTraffic(skip);
    }
}
