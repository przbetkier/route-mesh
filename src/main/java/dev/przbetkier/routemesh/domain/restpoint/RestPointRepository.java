package dev.przbetkier.routemesh.domain.restpoint;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestPointRepository extends Neo4jRepository<RestPoint, Long> {

    @Query(value = "MATCH (rp: RestPoint) WHERE id(rp) = $restPointId "
            + "MATCH (n: Node) WHERE id(n) = $nodeId "
            + "MERGE (n)-[:IS_RESTPOINT]->(rp)")
    void linkRestPointToNode(Long restPointId, Long nodeId);

    @Query(value = "MATCH (r: RestPoint) WHERE id(r)=$id DETACH DELETE r")
    void deleteRestpoint(Long id);

    List<RestPoint> findAll();
}
