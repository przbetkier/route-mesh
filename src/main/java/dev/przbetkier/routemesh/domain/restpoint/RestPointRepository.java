package dev.przbetkier.routemesh.domain.restpoint;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestPointRepository extends Neo4jRepository<RestPoint, Long> {

    @Query(value = "MATCH (rp: RestPoint) WHERE id(rp) = $restPointId "
            + "MATCH (r: Road) WHERE id(r) = $roadId "
            + "MERGE (rp)-[:IS_LOCATED_ON]->(r)")
    void linkRestPointToRoad(Long restPointId, Long roadId);
}
