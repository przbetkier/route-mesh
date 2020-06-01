package dev.przbetkier.routemesh.domain.roundabout;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface RoundaboutRepository extends Neo4jRepository<Roundabout, Long> {

    @Query(value = "MATCH (r: Roundabout) WHERE id(r)=$id "
            + "MATCH (n: Node) WHERE id(n)=$nodeId "
            + "MERGE (n)-[:IS_ROUNDABOUT]->(r)")
    void linkToNode(Long id, Long nodeId);

    List<Roundabout> findAll();

    @Query(value = "MATCH (r: Roundabout) WHERE id(r)=$id DETACH DELETE r")
    void deleteRoundabout(Long id);
}
