package dev.przbetkier.routemesh.domain.road;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Query("MATCH (r: Road) WHERE id(r)=$roadId\n"
            + "SET r.trafficFactor=$trafficFactor")
    void setTrafficFactor(Long roadId, Double trafficFactor);

}
