package dev.przbetkier.routemesh.domain.node;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NodeRepository extends Neo4jRepository<Node, Long> {

    List<Node> findAll();
}
