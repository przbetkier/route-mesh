package dev.przbetkier.routemesh.domain.road;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoadRepository extends Neo4jRepository<Road, Long> {

    List<Road> findAll();

    Page<Road> findAll(Pageable pageable);

    Page<Road> findAllByNameContainingIgnoreCase(String name, Pageable pageable);

    @Query("MATCH (r:Road) WHERE id(r)=$roadId OPTIONAL MATCH (r)<-[obstructs:OBSTRUCTS]-(o:Obstacle)-[h:HAS]->(ob:Obstruction) DETACH DELETE r,obstructs,o,h,ob")
    void deleteRoadAndConnectedObstacles(Long roadId);

    @Query("MATCH (r:Road)\n"
            + "MATCH (r)-[:STARTS]-(sn:Node)\n"
            + "MATCH (r)-[:ENDS]-(en:Node)\n"
            + "RETURN id(r) as roadId, sn.latitude as startLatitude, sn.longitude as startLongitude, en.latitude as "
            + "endLatitude, en.longitude as endLongitude")
    List<RoadCords> getAllRoadCords();

    @Query("MATCH (s1:Subnode)-[r:ROAD]->(s2:Subnode) WITH s1, r, s2 SKIP $skip LIMIT 1\n"
            + "MATCH (n1:Node), (n2:Node)\n"
            + "WHERE ID(n1)=s1.subID AND ID(n2)=s2.subID\n"
            + "RETURN r.subID as roadId, n1.latitude as startLatitude, n1.longitude as startLongitude, n2.latitude as endLatitude, n2.longitude as endLongitude"
    )
    Optional<RoadCords> getRoadForTraffic(Integer skip);
}
