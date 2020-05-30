package dev.przbetkier.routemesh.domain.roundabout;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface RoundaboutRepository extends Neo4jRepository<Roundabout, Long> {

    @Query(value = "MATCH (r: Roundabout) WHERE id(r)=$id "
            + "MATCH (n: Node) WHERE id(n)=$nodeId "
            + "MERGE (n)-[:IS_ROUNDABOUT]->(r)")
    void linkToNode(Long id, Long nodeId);

    @Query(value = "MATCH (r: Roundabout) WHERE id(r)=$id "
            + "MATCH (road: Road) WHERE id(road)=$roadId "
            + "MERGE (r)-[:EXIT {enterAngle: $enterAngle, exitAngle: $exitAngle}]->(road)")
    void linkToRoad(Long id, Long roadId, Integer enterAngle, Integer exitAngle);

    List<Roundabout> findAll();
}
