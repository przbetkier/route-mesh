package dev.przbetkier.routemesh.api;

import dev.przbetkier.routemesh.api.response.NodeResponse;
import dev.przbetkier.routemesh.domain.node.Node;
import dev.przbetkier.routemesh.domain.node.NodeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/nodes")
class NodeEndpoint {

    private final NodeService nodeService;

    NodeEndpoint(NodeService nodeService) {
        this.nodeService = nodeService;
    }

    @GetMapping
    public List<NodeResponse> getNodes() {
        return nodeService.getAllNodes().stream().map(NodeResponse::fromNode).collect(Collectors.toList());
    }

    @GetMapping("/{nodeId}")
    public NodeResponse getNode(@PathVariable Long nodeId) {
        return Optional.ofNullable(nodeService.getNode(nodeId)).map(NodeResponse::fromNode).orElse(null);
    }
}
